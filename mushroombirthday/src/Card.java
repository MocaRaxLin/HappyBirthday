import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;


public class Card {
	private float positionX;
	private float positionY;
	private float positionZ;
	private GL2 gl;
	private Texture cardTexture;
	public Card(GL2 inGl, float x, float y, float z, Texture incardTexture){
		gl = inGl;
		positionX = x;
		positionY = y;
		positionZ = z;
		cardTexture = incardTexture;
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glTranslated(positionX, positionY, positionZ);
		drawCard();
		gl.glTranslated(-positionY, -positionY, -positionZ);
	}
	private void drawCard() {
		cardTexture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(-30, 0, 0);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(30, 0, 0);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(30, 0, 40);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glVertex3f(-30, 0, 40);
		gl.glEnd();
	}
}
