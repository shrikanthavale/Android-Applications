package at.fhooe.mcm.it.adid.controller.cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import at.fhooe.mcm.it.adid.R;
import at.fhooe.mcm.it.adid.model.Step;
import at.fhooe.mcm.it.adid.model.cube.CubeMath;
import at.fhooe.mcm.it.adid.model.cube.CubeSides;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
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
public class SceneRenderer implements Renderer {

	/**
	 * strings for the textures
	 */
	private static final String[] CUBESIDE_TEXTURES = new String[] {
			"analysis_1", "analysis_2", "analysis_3", "analysis_4",
			"analysis_5", "analysis_6", "analysis_7", "analysis_8",
			"analysis_9", "analysis_10", "analysis_quiz", "design_1",
			"design_2", "design_3", "design_4", "design_5", "design_6",
			"design_7", "design_8", "design_9", "design_10", "design_quiz",
			"implementation_1", "implementation_2", "implementation_3",
			"implementation_quiz", "deployment_1", "deployment_2",
			"deployment_3", "deployment_quiz" };

	/**
	 * delay between each movement of the cube to its final position in the
	 * method stopDrag
	 */
	protected static final int TIMER_DELAY = 5;

	/**
	 * pi/2
	 */
	protected static final float HALF_OF_PI = (float) (Math.PI / 2);

	/**
	 * mulitplier for the movement speed
	 */
	protected static final float TURN_TO_POSITION_MULITPLIER = 10f;

	/**
	 * the frame buffer
	 */
	private FrameBuffer mFrameBuffer;

	/**
	 * the world object
	 */
	protected World mWorld;

	/**
	 * the cube object
	 */
	protected Object3D mCube;

	/**
	 * the cube up plane object
	 */
	protected Object3D mUpCubeObject;

	/**
	 * the cube down plane object
	 */
	protected Object3D mDownCubeObject;

	/**
	 * the cube left plane object
	 */
	protected Object3D mLeftCubeObject;

	/**
	 * the cube right plane object
	 */
	protected Object3D mRightCubeObject;

	/**
	 * the cube front plane object
	 */
	protected Object3D mFrontCubeObject;

	/**
	 * the cube back plane object
	 */
	protected Object3D mBackCubeObject;

	/**
	 * the camera object
	 */
	protected Camera mCamera;

	/**
	 * the light object
	 */
	protected Light mLight;

	/**
	 * color of the back of the scene
	 */
	protected RGBColor mSceneBackColor;

	/**
	 * the rotation vector of the cube
	 */
	protected SimpleVector mCubeRotateVector;

	/**
	 * the resources
	 */
	protected Resources mResources;

	/**
	 * a timer for completion of a cube rotation
	 */
	protected Timer mCompleteRotationTimer;

	/**
	 * the cube listeners
	 */
	protected List<CubeListener> mListenerList;

	/**
	 * the cube sides
	 */
	protected CubeSides mCubeSides;

	/**
	 * the current cube side
	 */
	private Step mCurrentCubeSide;

	/**
	 * flag if the cube is dragged horizontally or vertically
	 */
	protected boolean mIsHorizontalChange;

	/**
	 * the texture manager
	 */
	protected TextureManager mTextureManager;

	/**
	 * the drag speed factor according to the screen width
	 */
	protected float mDragSpeedFactor;

	/**
	 * the 3d plane objects
	 */
	protected Object3D[] m3DObjects;

	/**
	 * the 3d cube objects
	 */
	protected Object3D[] m3DCube;

	/**
	 * Constructor.
	 * 
	 * @param _context
	 *            The context in which the object gets constructed.
	 */
	public SceneRenderer(Context _context) {
		this.mResources = _context.getResources();

		Texture.defaultTo4bpp(true);
		mCubeSides = new CubeSides();
		mCurrentCubeSide = Step.ANALYSIS_1;
		mListenerList = new ArrayList<CubeListener>();
		mTextureManager = TextureManager.getInstance();
		mSceneBackColor = new RGBColor(253, 248, 247);
		initTextures();
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

	// private functions

	/**
	 * Initializes the scene. World, light, camera and skybox will be added and
	 * all objects get loaded, added to world and textures will be set.
	 */
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
		mFrontCubeObject.setTexture("analysis_1");
		mFrontCubeObject.strip();
		mFrontCubeObject.build();
		mFrontCubeObject.setRotationPivot(rotationPivot);
		mWorld.addObject(mFrontCubeObject);

		mUpCubeObject = m3DObjects[3];
		mUpCubeObject.calcTextureWrapSpherical();
		mUpCubeObject.setTexture("analysis_2");
		mUpCubeObject.strip();
		mUpCubeObject.build();
		mUpCubeObject.setRotationPivot(rotationPivot);
		mUpCubeObject.rotateX(HALF_OF_PI);
		mWorld.addObject(mUpCubeObject);

		mDownCubeObject = m3DObjects[4];
		mDownCubeObject.calcTextureWrapSpherical();
		mDownCubeObject.setTexture("analysis_1");
		mDownCubeObject.strip();
		mDownCubeObject.build();
		mDownCubeObject.setRotationPivot(rotationPivot);
		mDownCubeObject.rotateX(-HALF_OF_PI);
		mWorld.addObject(mDownCubeObject);

		mLeftCubeObject = m3DObjects[1];
		mLeftCubeObject.calcTextureWrapSpherical();
		mLeftCubeObject.setTexture("analysis_1");
		mLeftCubeObject.strip();
		mLeftCubeObject.build();
		mLeftCubeObject.setRotationPivot(rotationPivot);
		mLeftCubeObject.rotateY(HALF_OF_PI);
		mWorld.addObject(mLeftCubeObject);

		mRightCubeObject = m3DObjects[2];
		mRightCubeObject.calcTextureWrapSpherical();
		mRightCubeObject.setTexture("analysis_1");
		mRightCubeObject.strip();
		mRightCubeObject.build();
		mRightCubeObject.setRotationPivot(rotationPivot);
		mRightCubeObject.rotateY(-HALF_OF_PI);
		mWorld.addObject(mRightCubeObject);

		mBackCubeObject = m3DObjects[5];
		mBackCubeObject.calcTextureWrapSpherical();
		mBackCubeObject.setTexture("analysis_1");
		mBackCubeObject.strip();
		mBackCubeObject.build();
		mBackCubeObject.setRotationPivot(rotationPivot);
		mBackCubeObject.rotateY((float) Math.PI);
		mWorld.addObject(mBackCubeObject);

		mCamera = mWorld.getCamera();
		mCamera.setPosition(0, 0, -50);
		mCamera.lookAt(mCube.getCenter());

		mLight = new Light(mWorld);
		mLight.setIntensity(180, 180, 180);
		mLight.setPosition(new SimpleVector(10, -10, -100));
		MemoryHelper.compact();

		setCubeSideObjects();
	}

	/**
	 * Initializes the textures.
	 */
	protected void initTextures() {

		if (!mTextureManager.containsTexture("texture")) {
			Texture cubeTexture = new Texture(BitmapHelper.rescale(BitmapHelper
					.convert(mResources
							.getDrawable(R.drawable.cube_mustard_texture_new)),
					64, 64));
			mTextureManager.addTexture("texture", cubeTexture);
		}

		if (!mTextureManager.containsTexture("analysis_1")) {
			Texture texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_1));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[0], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_2));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[1], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_3));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[2], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_4));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[3], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_5));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[4], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_6));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[5], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_7));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[6], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_8));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[7], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_9));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[8], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_10));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[9], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.analysis_quiz));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[10], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_1));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[11], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_2));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[12], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_3));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[13], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_4));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[14], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_5));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[15], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_6));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[16], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_7));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[17], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_8));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[18], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_9));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[19], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_10));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[20], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.design_quiz));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[21], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.implementation_1));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[22], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.implementation_2));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[23], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.implementation_3));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[24], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.implementation_quiz));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[25], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.deployment_1));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[26], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.deployment_2));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[27], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.deployment_3));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[28], texture);

			texture = new Texture(
					mResources.openRawResource(R.drawable.deployment_quiz));
			mTextureManager.addTexture(CUBESIDE_TEXTURES[29], texture);

		}
	}

	/**
	 * Dragging the cube will be continued.
	 * 
	 * @param _dragWidth
	 *            The width of the dragging.
	 * @param _dragHeight
	 *            The height of the dragging.
	 */
	public void continueDragCube(float _dragWidth, float _dragHeight) {
		// set rotation axis depending of higher delta
		if (this.mCubeRotateVector == null) {
			// the width of the drag is bigger than the height of the drag
			if (Math.abs(_dragWidth) > Math.abs(_dragHeight)) {
				this.mCubeRotateVector = new SimpleVector(0, 1, 0);
			}
			// the width of the drag is smaller than the height of the drag
			else if (Math.abs(_dragWidth) < Math.abs(_dragHeight)) {
				this.mCubeRotateVector = new SimpleVector(-1, 0, 0);
			} else {
				return;
			}
		}

		// rotate cube
		float rotationAngle;

		// the width of the drag is bigger than the height of the drag
		if (this.mCubeRotateVector.equals(new SimpleVector(0, 1, 0))) {

			rotationAngle = _dragWidth * mDragSpeedFactor;
			if (mCubeRotateVector != null) {
				this.mCube.rotateAxis(mCubeRotateVector, rotationAngle);
				mUpCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mDownCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mLeftCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mRightCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mFrontCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mBackCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
			}

		}
		// the width of the drag is smaller than the height of the drag
		else {
			rotationAngle = _dragHeight * mDragSpeedFactor;
			if (mCubeRotateVector != null) {
				this.mCube.rotateAxis(mCubeRotateVector, rotationAngle);
				mUpCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mDownCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mLeftCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mRightCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mFrontCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
				mBackCubeObject.rotateAxis(mCubeRotateVector, rotationAngle);
			}

		}
	}

	/**
	 * Dragging the cube has been stopped so the cube has to be rotated with the
	 * same drag speed to the nearest cube side.
	 */
	public void stopDragCube() {
		mCompleteRotationTimer = new Timer();

		mCompleteRotationTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				float currentAngleRad = 0;
				float currentAngleDeg = 0;
				float rotation_complete = 0;

				if (mCubeRotateVector != null) {

					// determine rotation axis and calculate correct angle
					if (mCubeRotateVector.equals(new SimpleVector(0, 1, 0))) {
						mIsHorizontalChange = true;

						currentAngleRad = (float) (Math.atan2(mCube
								.getRotationMatrix().getZAxis().x, mCube
								.getRotationMatrix().getZAxis().z) - Math
								.atan2(mCamera.getZAxis().x,
										mCamera.getZAxis().z));

						currentAngleDeg = (float) CubeMath
								.getAbsoluteAngle((float) Math
										.toDegrees(currentAngleRad));
						rotation_complete = TURN_TO_POSITION_MULITPLIER;
					} else if (mCubeRotateVector.equals(new SimpleVector(-1, 0,
							0))) {
						mIsHorizontalChange = false;

						currentAngleRad = (float) (Math.atan2(mCube
								.getRotationMatrix().getYAxis().y, mCube
								.getRotationMatrix().getYAxis().z) - Math
								.atan2(mCamera.getZAxis().y,
										mCamera.getZAxis().z));

						currentAngleDeg = (float) CubeMath
								.getAbsoluteAngle((float) Math
										.toDegrees(currentAngleRad));
						rotation_complete = TURN_TO_POSITION_MULITPLIER;
					}

					// factor which equals rotation in rad
					float factor = (float) Math.toDegrees(mDragSpeedFactor
							* TURN_TO_POSITION_MULITPLIER);

					// > 0 < 90
					if (currentAngleDeg >= 0 && currentAngleDeg <= 90) {
						if (currentAngleDeg > 45) {
							if (currentAngleDeg + factor > 90) {
								normalizeRotationMatrix();
								mCubeRotateVector = null;
								this.cancel();
								mCompleteRotationTimer.cancel();
								if (mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getRightSideOfCubeSideAt(mCurrentCubeSide));
								}
							} else {
								continueDragCube(-rotation_complete,
										-rotation_complete);
							}
						} else {
							if (currentAngleDeg - factor > 0) {
								continueDragCube(rotation_complete,
										rotation_complete);
							} else {
								normalizeRotationMatrix();
								mCubeRotateVector = null;
								this.cancel();
								mCompleteRotationTimer.cancel();
								if (!mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getUpperSideOfCubeSideAt(mCurrentCubeSide));
								}
							}
						}
					}

					// > 90 < 180
					else if (currentAngleDeg > 90 && currentAngleDeg <= 180) {
						if (currentAngleDeg > 90 + 45) {
							if (currentAngleDeg + factor > 180) {
								normalizeRotationMatrix();
								mCubeRotateVector = null;
								this.cancel();
								mCompleteRotationTimer.cancel();
								if (!mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getDownSideOfCubeSideAt(mCurrentCubeSide));
								} else {
									setBackCubePosition(mCubeSides
											.getBackSideOfCubeSideAt(mCurrentCubeSide));
								}

							} else {
								continueDragCube(-rotation_complete,
										-rotation_complete);
							}
						} else {
							if (currentAngleDeg - factor > 90) {
								continueDragCube(rotation_complete,
										rotation_complete);
							} else {
								normalizeRotationMatrix();
								this.cancel();
								mCompleteRotationTimer.cancel();
								mCubeRotateVector = null;
								if (mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getRightSideOfCubeSideAt(mCurrentCubeSide));
								}
							}
						}
					}

					// > 180 < 270
					else if (currentAngleDeg > 180 && currentAngleDeg <= 270) {
						if (currentAngleDeg > 180 + 45) {
							if (currentAngleDeg + factor > 270) {
								normalizeRotationMatrix();
								this.cancel();
								mCompleteRotationTimer.cancel();
								mCubeRotateVector = null;
								if (mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getLeftSideOfCubeSideAt(mCurrentCubeSide));
								}
							} else {
								continueDragCube(-rotation_complete,
										-rotation_complete);
							}
						} else {
							if (currentAngleDeg - factor > 180) {
								continueDragCube(rotation_complete,
										rotation_complete);
							} else {
								normalizeRotationMatrix();
								mCubeRotateVector = null;
								this.cancel();
								mCompleteRotationTimer.cancel();
								if (!mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getDownSideOfCubeSideAt(mCurrentCubeSide));
								} else {
									setBackCubePosition(mCubeSides
											.getBackSideOfCubeSideAt(mCurrentCubeSide));
								}

							}
						}
					}
					// > 270 < 360
					else if (currentAngleDeg > 270 && currentAngleDeg <= 360) {
						if (currentAngleDeg > 270 + 45) {
							if (currentAngleDeg + factor > 359) {
								normalizeRotationMatrix();
								mCubeRotateVector = null;
								this.cancel();
								mCompleteRotationTimer.cancel();
								if (!mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getUpperSideOfCubeSideAt(mCurrentCubeSide));
								}
							} else {
								continueDragCube(-rotation_complete,
										-rotation_complete);
							}
						} else {
							if (currentAngleDeg - factor > 270) {
								continueDragCube(rotation_complete,
										rotation_complete);
							} else {
								normalizeRotationMatrix();
								this.cancel();
								mCompleteRotationTimer.cancel();
								mCubeRotateVector = null;
								if (mIsHorizontalChange) {
									setBackCubePosition(mCubeSides
											.getLeftSideOfCubeSideAt(mCurrentCubeSide));
								}
							}
						}
					}

					else if (currentAngleDeg < 0.0f) {
						normalizeRotationMatrix();
						mCubeRotateVector = null;
						this.cancel();
						mCompleteRotationTimer.cancel();
					}
				} else {
					this.cancel();
					mCompleteRotationTimer.cancel();
				}
			}

			/**
			 * Normalizes the rotation matrices, so that the position of the
			 * cube objects is at a fixed position again without any small float
			 * differences.
			 */
			private void normalizeRotationMatrix() {
				mCube.setRotationMatrix(CubeMath.normalizeRotationMatrix(mCube
						.getRotationMatrix()));
				mUpCubeObject.setRotationMatrix(CubeMath
						.normalizeRotationMatrix(mUpCubeObject
								.getRotationMatrix()));
				mDownCubeObject.setRotationMatrix(CubeMath
						.normalizeRotationMatrix(mDownCubeObject
								.getRotationMatrix()));
				mLeftCubeObject.setRotationMatrix(CubeMath
						.normalizeRotationMatrix(mLeftCubeObject
								.getRotationMatrix()));
				mRightCubeObject.setRotationMatrix(CubeMath
						.normalizeRotationMatrix(mRightCubeObject
								.getRotationMatrix()));
				mFrontCubeObject.setRotationMatrix(CubeMath
						.normalizeRotationMatrix(mFrontCubeObject
								.getRotationMatrix()));
				mBackCubeObject.setRotationMatrix(CubeMath
						.normalizeRotationMatrix(mBackCubeObject
								.getRotationMatrix()));
			}
		}, 0, TIMER_DELAY);
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
		CubeChangedEvent _event = new CubeChangedEvent(this,
				mCubeSides.getCurrentSideOfCubeSideAt(mCurrentCubeSide));
		for (int i = 0; i < mListenerList.size(); i++) {
			((CubeListener) mListenerList.get(i)).cubeHasChanged(_event);
		}

	}

	/**
	 * Sets back the cube and side objects positions so that the front side is
	 * again on the front and all textures are shown normal and not rotated or
	 * upside down.
	 * 
	 * @param _newCubeSide
	 *            The new side of the cube that is on the front.
	 */
	private void setBackCubePosition(Step _newCubeSide) {
		mCurrentCubeSide = _newCubeSide;
		fireCubeHasChangedEvent();
		Matrix cubeStartMatrix = new Matrix();
		cubeStartMatrix.setRow(0, 1, 0, 0, 0);
		cubeStartMatrix.setRow(1, 0, 1, 0, 0);
		cubeStartMatrix.setRow(2, 0, 0, 1, 0);
		cubeStartMatrix.setRow(3, 0, 0, 0, 1);
		mCube.setRotationMatrix(cubeStartMatrix);
		setCubeSideObjects();
	}

	public void setCubeBackToStartPosition() {
		mCurrentCubeSide = Step.ANALYSIS_1;
		Matrix cubeStartMatrix = new Matrix();
		cubeStartMatrix.setRow(0, 1, 0, 0, 0);
		cubeStartMatrix.setRow(1, 0, 1, 0, 0);
		cubeStartMatrix.setRow(2, 0, 0, 1, 0);
		cubeStartMatrix.setRow(3, 0, 0, 0, 1);
		mCube.setRotationMatrix(cubeStartMatrix);
		setCubeSideObjects();
		fireCubeHasChangedEvent();
	}

	/**
	 * Sets back the rotation matrix of the side objects and adds the correct
	 * texture to them.
	 */
	private void setCubeSideObjects() {

		// set front texture
		mFrontCubeObject.setTexture(CUBESIDE_TEXTURES[mCubeSides
				.getCurrentSideOfCubeSideAt(mCurrentCubeSide).getValue()]);
		onDrawFrame(null);

		// create rotation matrices
		Matrix frontMatrix = new Matrix();
		frontMatrix.setRow(0, 1, 0, 0, 0);
		frontMatrix.setRow(1, 0, 1, 0, 0);
		frontMatrix.setRow(2, 0, 0, 1, 0);
		frontMatrix.setRow(3, 0, 0, 0, 1);

		Matrix rightMatrix = new Matrix();
		rightMatrix.setRow(0, 0, 0, 1, 0);
		rightMatrix.setRow(1, 0, 1, 0, 0);
		rightMatrix.setRow(2, -1, 0, 0, 0);
		rightMatrix.setRow(3, 0, 0, 0, 1);

		Matrix leftMatrix = new Matrix();
		leftMatrix.setRow(0, 0, 0, -1, 0);
		leftMatrix.setRow(1, 0, 1, 0, 0);
		leftMatrix.setRow(2, 1, 0, 0, 0);
		leftMatrix.setRow(3, 0, 0, 0, 1);

		Matrix topMatrix = new Matrix();
		topMatrix.setRow(0, 1, 0, 0, 0);
		topMatrix.setRow(1, 0, 0, -1, 0);
		topMatrix.setRow(2, 0, 1, 0, 0);
		topMatrix.setRow(3, 0, 0, 0, 1);

		Matrix bottomMatrix = new Matrix();
		bottomMatrix.setRow(0, 1, 0, 0, 0);
		bottomMatrix.setRow(1, 0, 0, 1, 0);
		bottomMatrix.setRow(2, 0, -1, 0, 0);
		bottomMatrix.setRow(3, 0, 0, 0, 1);

		Matrix backMatrix = new Matrix();
		backMatrix.setRow(0, -1, 0, 0, 0);
		backMatrix.setRow(1, 0, 1, 0, 0);
		backMatrix.setRow(2, 0, 0, -1, 0);
		backMatrix.setRow(3, 0, 0, 0, 1);

		// set rotation matrices
		mFrontCubeObject.setRotationMatrix(frontMatrix);
		mRightCubeObject.setRotationMatrix(rightMatrix);
		mLeftCubeObject.setRotationMatrix(leftMatrix);
		mUpCubeObject.setRotationMatrix(topMatrix);
		mDownCubeObject.setRotationMatrix(bottomMatrix);
		mBackCubeObject.setRotationMatrix(backMatrix);

		onDrawFrame(null);

		// set texture of other sides than front
		mUpCubeObject.setTexture(CUBESIDE_TEXTURES[mCubeSides
				.getCurrentSideOfCubeSideAt(
						mCubeSides.getUpperSideOfCubeSideAt(mCurrentCubeSide))
				.getValue()]);
		mRightCubeObject.setTexture(CUBESIDE_TEXTURES[mCubeSides
				.getCurrentSideOfCubeSideAt(
						mCubeSides.getRightSideOfCubeSideAt(mCurrentCubeSide))
				.getValue()]);
		mDownCubeObject.setTexture(CUBESIDE_TEXTURES[mCubeSides
				.getCurrentSideOfCubeSideAt(
						mCubeSides.getDownSideOfCubeSideAt(mCurrentCubeSide))
				.getValue()]);
		mLeftCubeObject.setTexture(CUBESIDE_TEXTURES[mCubeSides
				.getCurrentSideOfCubeSideAt(
						mCubeSides.getLeftSideOfCubeSideAt(mCurrentCubeSide))
				.getValue()]);
		mBackCubeObject.setTexture(CUBESIDE_TEXTURES[mCubeSides
				.getCurrentSideOfCubeSideAt(
						mCubeSides.getBackSideOfCubeSideAt(mCurrentCubeSide))
				.getValue()]);
	}

	/**
	 * Returns the current cube side.
	 * 
	 * @return The current cube side.
	 */
	public Step getCurrentCubeSide() {
		return mCurrentCubeSide;
	}

	/**
	 * Sets the current cube side.
	 * 
	 * @param _currentCubeSide
	 */
	public void setCurrentCubeSide(Step _currentCubeSide) {
		mCurrentCubeSide = _currentCubeSide;
	}

	/**
	 * Returns the current cube side of the CubeSides object.
	 * 
	 * @return The current cube side of the CubeSides object.
	 */
	public Step getCurrentSideOfCubeSide() {
		return mCubeSides.getCurrentSideOfCubeSideAt(mCurrentCubeSide);
	}

	/**
	 * Returns the front side object of the cube.
	 * 
	 * @return The front side object of the cube
	 */
	public Object3D getFrontSideObject() {
		return mFrontCubeObject;
	}

	/**
	 * Returns the cube object.
	 * 
	 * @return The cube object.
	 */
	public Object3D getCube() {
		return mCube;
	}
}
