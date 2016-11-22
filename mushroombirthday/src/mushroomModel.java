import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class mushroomModel {
	private float positionX;
	private float positionY;
	private float positionZ;
	private GL2 gl;
	private GLU glu;
	private Texture hatTexture;
	private Texture rootTexture;
	public mushroomModel(GL2 inGl, GLU inGlu, float x, float y, float z,Texture inHatTexture, Texture inRootTexture) {
		positionX = x;
		positionY = y;
		positionZ = z;
		gl = inGl;
		glu = inGlu;
		hatTexture = inHatTexture;
		rootTexture = inRootTexture;
		gl.glTranslatef(positionX, positionY, positionZ);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		drawRoot();
		drawHat();
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glTranslatef(-positionX, -positionY, -positionZ-8);
	}
	private void drawRoot() {
		rootTexture.bind(gl);
		GLUquadric root = glu.gluNewQuadric();
		
		glu.gluQuadricTexture(root, true);
		glu.gluQuadricDrawStyle(root, GLU.GLU_FILL);
		glu.gluQuadricNormals(root, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(root, GLU.GLU_OUTSIDE);
		glu.gluDisk(root, 0, 4, 32, 1);
		glu.gluCylinder(root, 4, 2, 13, 32, 16);
		glu.gluDeleteQuadric(root);
	}
	private void drawHat() {
		hatTexture.bind(gl);
		GLUquadric hat = glu.gluNewQuadric();
		gl.glTranslatef(0, 0, 13);
		glu.gluQuadricTexture(hat, true);
		glu.gluQuadricDrawStyle(hat, GLU.GLU_FILL);
		glu.gluQuadricNormals(hat, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(hat, GLU.GLU_OUTSIDE);
		glu.gluDisk(hat, 0, 4, 32, 1);
		gl.glTranslatef(0, 0, -2);
		//將xyz軸做(a,b,c)的向量平移
		glu.gluCylinder(hat, 7, 4, 2, 32, 16);
		gl.glTranslatef(0, 0, -3);
		glu.gluCylinder(hat, 9, 7, 3, 32, 16);
		glu.gluDeleteQuadric(hat);
	}

	
}
