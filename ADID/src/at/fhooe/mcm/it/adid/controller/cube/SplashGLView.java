package at.fhooe.mcm.it.adid.controller.cube;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * The GlSurfaceView of the SplashActivity on which the cube is drawn
 * 
 * @author Mario Kraml
 *
 */
public class SplashGLView extends GLSurfaceView {

	private SplashSceneRenderer mRenderer;
	private Context mContext;


	/**
	 * The Contructor.
	 * 
	 * @param context	The application context
	 * @param attributeSet	The specified AttributeSet
	 */
	public SplashGLView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.mContext = context;
		initRenderer();
	}
	
	/**
	 * Initializes the renderer for the GLSurfaceView
	 */
	private void initRenderer() {
		this.mRenderer = new SplashSceneRenderer(this.mContext);
		this.setRenderer(mRenderer);
	}

	/**
	 * Pause the GLSurfaceView. Called when the activity gets paused.
	 */
	public void pause() {
		this.onPause();
	}

	/**
	 * Resume the GLSurfaceView. Called when the activity gets resumed.
	 */
	public void resume() {
		this.onResume();
	}
	
	/**
	 * Getter for the renderer of this GLSurfaceView
	 * 
	 * @return The renderer of this GLSurfaceView
	 */
	public SplashSceneRenderer getSceneRenderer() {
		return mRenderer;
	}
}
