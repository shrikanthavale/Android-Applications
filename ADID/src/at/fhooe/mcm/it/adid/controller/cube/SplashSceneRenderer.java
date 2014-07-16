package at.fhooe.mcm.it.adid.controller.cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.Step;
import at.fhooe.mcm.it.adid.model.cube.CubeSides;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

/**
 * The class that represents the Renderer for the MainGLView.
 * 
 * @author Mario Kraml
 */
public class SplashSceneRenderer implements Renderer {

	private FrameBuffer mFrameBuffer;

	protected World mWorld;
	protected Object3D mCube;
	protected Object3D mUpCubeObject;
	protected Object3D mDownCubeObject;
	protected Object3D mLeftCubeObject;
	protected Object3D mRightCubeObject;
	protected Object3D mFrontCubeObject;
	protected Object3D mBackCubeObject;
	protected Camera mCamera;
	protected Light mLight;
	protected RGBColor mSceneBackColor;

	protected SimpleVector mCubeRotateVector;
	protected Resources mResources;
	protected Timer mCompleteRotationTimer;
	protected List<CubeListener> mListenerList;

	protected CubeSides mCubeSides;
	protected boolean mIsHorizontalChange;

	protected TextureManager mTextureManager;
	protected float mDragSpeedFactor;
	protected Object3D[] m3DObjects;
	protected Object3D[] m3DCube;
	
	private long countStart;

	/**
	 * Constructor.
	 * 
	 * @param _context
	 *            The context in which the object gets constructed.
	 */
	public SplashSceneRenderer(Context _context) {
		this.mResources = _context.getResources();

		Texture.defaultTo4bpp(true);
		mCubeSides = new CubeSides();
		mListenerList = new ArrayList<CubeListener>();
		mTextureManager = TextureManager.getInstance();
		mSceneBackColor = new RGBColor(253, 248, 247);
		initTextures();
		countStart = System.currentTimeMillis();
	}

	// overrides
	@Override
	public void onDrawFrame(GL10 _gl) {
		try {
			this.mFrameBuffer.clear();
			this.mFrameBuffer.clear(mSceneBackColor);
			this.mWorld.renderScene(this.mFrameBuffer);
			this.mWorld.draw(this.mFrameBuffer);
		} catch (Exception e) {
			Log.e("Exception", "onDraw Exception");
		}
		if (this.mFrameBuffer != null) {
			this.mFrameBuffer.display();
		}
		if(System.currentTimeMillis() - countStart > 2000){
			fireCubeHasChangedEvent();
		}
	}

	@Override
	public void onSurfaceChanged(GL10 _gl, int _width, int _height) {
		if (this.mFrameBuffer != null) {
			this.mFrameBuffer.dispose();
		}

		this.mFrameBuffer = new FrameBuffer(_gl, _width, _height);
		// set drag speed factor
		mDragSpeedFactor = ((float) (Math.PI) / (_width * 1.5f));
	}

	@Override
	public void onSurfaceCreated(GL10 _gl, EGLConfig _eglConfig) {
		initScene();
	}

	/**
	 * Returns the cube object.
	 * 
	 * @return The cube object.
	 */
	public Object3D getCube() {
		return mCube;
	}

	protected void initTextures() {
		if (!mTextureManager.containsTexture("texture")) {
			Texture cubeTexture = new Texture(BitmapHelper.rescale(BitmapHelper
					.convert(mResources
							.getDrawable(R.drawable.cube_mustard_texture_new)),
					64, 64));
			mTextureManager.addTexture("texture", cubeTexture);
		}
		if (!mTextureManager.containsTexture("splash_1")) {

			Texture texture = new Texture(
					mResources.openRawResource(R.drawable.splash_1));
			mTextureManager.addTexture("splash_1", texture);
		}
	}

	public void initScene() {
		mWorld = new World();
		mWorld.setAmbientLight(100, 100, 100);

		m3DCube = Loader.load3DS(mResources.openRawResource(R.raw.cube_solo),
				15);

		mCube = m3DCube[0];
		mCube.setTexture("texture");
		mCube.strip();
		mCube.build();
		mWorld.addObject(mCube);

		SimpleVector rotationPivot = mCube.getRotationPivot();
		m3DObjects = Loader.load3DS(
				mResources.openRawResource(R.raw.cube_planes), 15);

		mFrontCubeObject = m3DObjects[0];
		mFrontCubeObject.calcTextureWrapSpherical();
		mFrontCubeObject.setTexture("splash_1");
		mFrontCubeObject.strip();
		mFrontCubeObject.build();
		mFrontCubeObject.setRotationPivot(rotationPivot);
		mWorld.addObject(mFrontCubeObject);

		mCamera = mWorld.getCamera();
		mCamera.setPosition(0, 0, -50);
		mCamera.lookAt(mCube.getCenter());

		mLight = new Light(mWorld);
		mLight.setIntensity(180, 180, 180);
		mLight.setPosition(new SimpleVector(10, -10, -100));
		MemoryHelper.compact();
	}
	

	/**
	 * Adds a CubeListener to whom CubeChangedEvents get fired.
	 * 
	 * @param _listener
	 *            The CubeListener that will be added.
	 */
	public void addCubeListener(CubeListener _listener) {
		mListenerList.add(_listener);
	}

	/**
	 * Removes a CubeListener.
	 * 
	 * @param _listener
	 *            The CubeListener that will be removed.
	 */
	public void removeCubeListener(CubeListener _listener) {
		mListenerList.remove(_listener);
	}
	
	/**
	 * Fires a CubeChangedEvent with the current cube side to all the added
	 * CubeListeners.
	 */
	protected void fireCubeHasChangedEvent() {
		CubeChangedEvent _event = new CubeChangedEvent(this,Step.ANALYSIS_1);
		for (int i = 0; i < mListenerList.size(); i++) {
			((CubeListener) mListenerList.get(i)).cubeHasChanged(_event);
		}

	}
}
