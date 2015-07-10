import javax.media.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Door {
	private float positionX;
	private float positionY;
	private float positionZ;
	private GL2 gl;
	private Texture doorTexture;
	private boolean opened = false;

	public Door(GL2 inGl, float x, float y, float z, Texture indoorTexture,Boolean inopened) {
		gl = inGl;
		positionX = x;
		positionY = y;
		positionZ = z;
		doorTexture = indoorTexture;
		opened = inopened;
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glTranslatef(positionX, positionY, positionZ);

		drawDoor();
		if (opened) {
			gl.glTranslatef(-positionX - 10, -positionY - 10, -positionZ);
		} else {
			gl.glTranslatef(-positionX, -positionY, -positionZ);
		}
		 gl.glLoadIdentity();
	}

	private void drawDoor() {
		gl.glColor3f(1f, 0f, 0.8f);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex3f(0,0,25);
		gl.glVertex3f(8,0, 30);
		gl.glVertex3f(5, 0, 30);
		gl.glVertex3f(5, 0, 35);
		gl.glVertex3f(-5, 0, 35);
		gl.glVertex3f(-5, 0, 30);
		gl.glVertex3f(-8, 0, 30);
		gl.glVertex3f(0,0,25);
		gl.glEnd();
		
		gl.glColor3f(1f, 1f, 1f);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(-12, 0, 0);
		gl.glVertex3f(12, 0, 0);
		gl.glVertex3f(12, 0, 22);
		gl.glVertex3f(-12, 0, 22);
		gl.glEnd();

		if (opened) {

			gl.glColor3f(0f, 0f, 0f);
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3f(-10, 0, 0);
			gl.glVertex3f(10, 0, 0);
			gl.glVertex3f(10, 0, 20);
			gl.glVertex3f(-10, 0, 20);
			gl.glEnd();
			gl.glColor3f(1f, 1f, 1f);

			doorTexture.bind(gl);
			gl.glTranslatef(10, -10, 0);
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(0, -10, 0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(0, 10, 0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(0, 10, 20);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(0, -10, 20);
			gl.glEnd();
		} else {
			doorTexture.bind(gl);
			gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-10, 0, 0);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(10, 0, 0);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(10, 0, 20);
			gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(-10, 0, 20);
			gl.glEnd();
		}
	}

}
