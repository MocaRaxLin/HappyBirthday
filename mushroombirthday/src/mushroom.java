import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

@SuppressWarnings("serial")
public class mushroom extends GLCanvas implements GLEventListener, KeyListener,
		MouseWheelListener {
	private GLU glu;
	private Texture hatTexture;
	private Texture rootTexture;
	private Texture groundTexture;
	private Texture wallTexture;
	private float edgeRadius = 0;
	private float edgeHeight = 0;
	private int fps = 60;
	private FPSAnimator animator;
	private double camaraAngle = 0;
	private float viewAngle = 50f;
	mushroomModel mushroomModel;
	backGround backGround;
	private float mushroomSiteX = 0;
	private float mushroomSiteY = -20;
	private float mushroomSiteZ = 0;
	private float seeX = 0;
	private float seeY = 0;
	private float eyeX = 0;
	private float eyeY = 0;
	private Texture cakeSideTexture;
	private Texture cakeTopTexture;
	private Texture candelSideTexture;
	private Texture candleTopTexture;
	cake cake;
	private float cakeSiteX = 0;
	private float cakeSiteY = 250;
	private float cakeSiteZ = -1000;
	Door door;
	private float doorSiteX = 0;
	private float doorSiteY = 0;
	private float doorSiteZ = 0;
	private Texture doorTexture;
	MP3 mp3;
	Card card;
	private Texture cardTexture;
	private float cardSiteX = -2000;
	private float cardSiteY = 120;
	private float cardSiteZ = 0;
	
	public mushroom(GLCapabilities capabilities, int width, int height) {
		addGLEventListener(this);
		addKeyListener(this);
		setFocusable(true);
		addMouseWheelListener(this);
	}

	public void init(GLAutoDrawable drawable) {
		drawable.setGL(new DebugGL2(drawable.getGL().getGL2()));
		final GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		glu = new GLU();
		animator = new FPSAnimator(this, fps);
		animator.start();
		mp3 = new MP3();
		mp3.play();
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/mushroomHat.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			hatTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/mushroomRoot.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			rootTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/ground.jpg");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "jpg");
			groundTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass()
					.getResourceAsStream("/img/wall.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			wallTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/cakeSide.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			cakeSideTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/cakeUp.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			cakeTopTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass()
					.getResourceAsStream("/img/door.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			doorTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/candleSide.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			candelSideTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/candleTop.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			candleTopTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try {
			InputStream stream = getClass().getResourceAsStream(
					"/img/card.png");
			TextureData data = TextureIO.newTextureData(GLProfile.getDefault(),
					stream, false, "png");
			cardTexture = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		setCamera(gl, glu);
		
		mushroomModel = new mushroomModel(gl, glu, mushroomSiteX,
				mushroomSiteY, mushroomSiteZ, hatTexture, rootTexture);
		if (getInTheDoor()) {
			if (edgeRadius >= 2000) {
				edgeRadius = 2000;
				if (edgeHeight >= 8000) {
					edgeHeight = 8000;
					backGround = new backGround(gl, glu, groundTexture,
							wallTexture, edgeRadius, edgeHeight);
				}
				backGround = new backGround(gl, glu, groundTexture,
						wallTexture, edgeRadius, edgeHeight += 10);
			} else {
				backGround = new backGround(gl, glu, groundTexture,
						wallTexture, edgeRadius += 10, edgeHeight);
			}
			if (cakeSiteZ >= 0) {
				cakeSiteZ = 0;
				cake = new cake(gl, glu, cakeSiteX, cakeSiteY, cakeSiteZ,
						cakeSideTexture, cakeTopTexture, candelSideTexture,
						candleTopTexture);
			} else {
				cake = new cake(gl, glu, cakeSiteX, cakeSiteY, cakeSiteZ += 1,
						cakeSideTexture, cakeTopTexture, candelSideTexture,
						candleTopTexture);
			}
			if(cardSiteX>=0){
				cardSiteX=0;
				gl.glRotatef(40.0f, 0, 0,1);
				card = new Card(gl, cardSiteX, cardSiteY, cardSiteZ, cardTexture);
				gl.glRotatef(-40.0f, 0, 0,1);
			}else{
			gl.glRotatef(40.0f, 0, 0,1);
			card = new Card(gl, cardSiteX+=2, cardSiteY, cardSiteZ, cardTexture);
			gl.glRotatef(-40.0f, 0, 0,1);
			}
		} else {
			if (wantOpenDoor()) {
				door = new Door(gl, doorSiteX, doorSiteY, doorSiteZ,
						doorTexture, true);

			} else {
				door = new Door(gl, doorSiteX, doorSiteY, doorSiteZ,
						doorTexture, false);
			}
		}

	}

	public void dispose(GLAutoDrawable drawable) {
	}

	private void setCamera(GL2 gl, GLU glu2) {
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		float widthHeightRatio = (float) getWidth() / (float) getHeight();
		glu.gluPerspective(viewAngle, widthHeightRatio, 1, 10000);

		eyeX = (float) (50 * (Math.cos(camaraAngle - Math.PI / 2)));
		eyeY = (float) (50 * (Math.sin(camaraAngle - Math.PI / 2)));
		seeX = (float) Math.cos(camaraAngle + Math.PI / 2);
		seeY = (float) Math.sin(camaraAngle + Math.PI / 2);

		glu.gluLookAt(mushroomSiteX + eyeX, mushroomSiteY + eyeY,
				mushroomSiteZ + 10, mushroomSiteX + seeX, mushroomSiteY + seeY,
				mushroomSiteZ + 20, 0, 0, 1);

		/**
		 * glu.gluLookAt(cakeSiteX + 200 * Math.cos(a += 0.001), cakeSiteY + 200
		 * Math.sin(a += 0.001), cakeSiteZ + 500, cakeSiteX, cakeSiteY,
		 * cakeSiteZ, 0, 0, 1);
		 */
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glViewport(0, 0, width, height);
	}

	public final static void main(String args[]) {
		GLCapabilities capabilities = createGLCapabilities();
		mushroom canvas = new mushroom(capabilities, 1000, 600);
		JFrame frame = new JFrame("mushroomBirthday");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.requestFocus();
	}

	private static GLCapabilities createGLCapabilities() {
		GLCapabilities capabilities = new GLCapabilities(null);
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		return capabilities;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			mushroomSiteY += 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			mushroomSiteY -= 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (camaraAngle < -1.26) {
				camaraAngle = -1.26;
			}
			camaraAngle -= 0.01;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (camaraAngle > 1.26) {
				camaraAngle = 1.26;
			}
			camaraAngle += 0.01;
		}
	}

	public boolean wantOpenDoor() {
		if (doorSiteX - 10 < mushroomSiteX && mushroomSiteX < doorSiteX + 10
				&& doorSiteY - 10 < mushroomSiteY
				&& mushroomSiteY < doorSiteY + 10) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getInTheDoor() {
		if (doorSiteY + 10 <= mushroomSiteY) {
			return true;
		} else {
			return false;
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int notice = e.getWheelRotation();
		if (notice > 0) {
			viewAngle *= 1.2;
			if (viewAngle >= 87)
				viewAngle = 86.32f;
		}
		if (notice < 0) {
			viewAngle *= 0.83333;
			if (viewAngle <= 0)
				viewAngle = 1;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
