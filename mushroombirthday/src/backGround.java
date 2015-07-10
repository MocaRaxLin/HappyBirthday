import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;
public class backGround {
	private GL2 gl;
	private GLU glu;
	private final float CenterX = 0;
	private final float CenterY = 0;
	private final float CenterZ = 0;
	private float edgeRadius;//2000
	private float edgeHeight;//8000;
	private Texture ground;
	private Texture edgeWall;
	
	public backGround(GL2 inGl, GLU inGlu,Texture inGround,Texture inEdgeWall,float inedgeRadius,float inedgeHeight){
		gl = inGl;
		glu = inGlu;
		ground = inGround;
		edgeWall = inEdgeWall;
		edgeRadius = inedgeRadius;
		edgeHeight = inedgeHeight;
		gl.glTranslatef(CenterX, CenterY, CenterZ);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		drawGround();
		drawWall();
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}
	private void drawGround() {
		ground.bind(gl);
		GLUquadric ground = glu.gluNewQuadric();
		glu.gluQuadricTexture(ground, true);
		glu.gluQuadricDrawStyle(ground, GLU.GLU_FILL);
		glu.gluQuadricNormals(ground, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(ground, GLU.GLU_OUTSIDE);
		glu.gluDisk(ground, 0, edgeRadius, 64, 1);
		glu.gluDeleteQuadric(ground);
	}
	private void drawWall() {
		gl.glRotatef(180f, 0, 0, 1);
		edgeWall.bind(gl);
		GLUquadric edgeWall = glu.gluNewQuadric();
		glu.gluQuadricTexture(edgeWall, true);
		glu.gluQuadricDrawStyle(edgeWall, GLU.GLU_FILL);
		glu.gluQuadricNormals(edgeWall, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(edgeWall, GLU.GLU_OUTSIDE);
		glu.gluCylinder(edgeWall, edgeRadius, edgeRadius, edgeHeight, 64, 16);
		glu.gluDeleteQuadric(edgeWall);
		gl.glRotatef(180f, 0, 0, 1);
	}

}
