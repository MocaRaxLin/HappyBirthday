import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class cake {
	private float positionX;
	private float positionY;
	private float positionZ;
	private GL2 gl;
	private GLU glu;
	private Texture cakeSideTexture;
	private Texture cakeTopTexture;
	private Texture candleSideTexture;
	private Texture candleTopTexture;
	private float candleWidth = 2;
	private float candleHeight = 30;
	public cake(GL2 inGl, GLU inGlu, float x, float y, float z,
			Texture incakeSideTexture, Texture incakeTopTexture,
			Texture incandleSideTexture, Texture incandleTopTexture) {
		gl = inGl;
		glu = inGlu;
		positionX = x;
		positionY = y;
		positionZ = z;
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glTranslatef(positionX, positionY, positionZ);
		
		cakeSideTexture = incakeSideTexture;
		cakeTopTexture = incakeTopTexture;
		candleSideTexture = incandleSideTexture;
		candleTopTexture = incandleTopTexture;
		drawcake();
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glTranslatef(-positionX, -positionY, -positionZ-150);
	}

	private void drawcake() {
		GLUquadric cakeSide = glu.gluNewQuadric();
		glu.gluQuadricTexture(cakeSide, true);
		glu.gluQuadricDrawStyle(cakeSide, GLU.GLU_FILL);
		glu.gluQuadricNormals(cakeSide, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(cakeSide, GLU.GLU_OUTSIDE);
		for (int i = 0; i < 3; i++) {
		cakeSideTexture.bind(gl);
		glu.gluCylinder(cakeSide, 100-25*i, 100-25*i, 50, 64, 16);
		gl.glTranslatef(0,0,50);
		cakeTopTexture.bind(gl);
		glu.gluDisk(cakeSide, 0, 100-25*i, 64, 16);
		glu.gluDeleteQuadric(cakeSide);
		for(int j = 0;j<7-i;j++){
			double theta =j*2*Math.PI/(7-i);
			double trX = (90-25*i)*Math.cos(theta);
			double trY = (90-25*i)*Math.sin(theta);
			
			gl.glTranslated(trX, trY, 0);
			
			candleSideTexture.bind(gl);
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-candleWidth,-candleWidth, 0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(candleWidth,-candleWidth, 0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(candleWidth,-candleWidth, candleHeight);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(-candleWidth,-candleWidth, candleHeight);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(candleWidth,-candleWidth, 0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(candleWidth,candleWidth, 0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(candleWidth,candleWidth, candleHeight);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(candleWidth,-candleWidth, candleHeight);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(candleWidth,candleWidth, 0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(-candleWidth,candleWidth, 0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(-candleWidth,candleWidth, candleHeight);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(candleWidth,candleWidth, candleHeight);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-candleWidth,candleWidth, 0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(-candleWidth,-candleWidth, 0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(-candleWidth,-candleWidth, candleHeight);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(-candleWidth,candleWidth, candleHeight);
			gl.glEnd();
			
			candleTopTexture.bind(gl);
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-candleWidth,-candleWidth, candleHeight);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(candleWidth,-candleWidth, candleHeight);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(candleWidth,candleWidth, candleHeight);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(-candleWidth,candleWidth, candleHeight);
			gl.glEnd();
			gl.glTranslated(-trX, -trY, 0);
			}
		}
	}
}
