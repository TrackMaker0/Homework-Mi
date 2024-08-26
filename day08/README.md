# DAY08

## 自定义组件

### 自定义组件的三种方式

#### 组合原生控件
将自己需要的控件组合起来变成一个新控件,如下制作常见的app页面头部.
新建一个Android项目,创建一个头部布局view_top.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:background="#50e7ab"
    android:padding="10dp">
 
    <ImageView
        android:id="@+id/top_left"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@mipmap/fanhui_bai" />
 
    <TextView
        android:id="@+id/top_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:text="首页"
        android:textSize="17sp"
        android:textColor="#ffffff" />
 
    <TextView
        android:id="@+id/top_right"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="提交"
        android:textSize="17sp"
        android:textColor="#ffffff"
        android:layout_centerVertical="true"
        android:layout_alignParentRight="true" />
</RelativeLayout>
```

下面创建一个TopView继承RelativeLayout

```java
public class TopView extends RelativeLayout {
    // 返回按钮控件
    private ImageView top_left;
    // 标题Tv
    private TextView top_title;
 
    private TextView top_right;
 
    public TopView(Context context) {
        super(context);
    }
 
    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_top, this);
        // 获取控件
        top_left = (ImageView) findViewById(R.id.top_left);
        top_title = (TextView) findViewById(R.id.top_title);
        top_right = (TextView) findViewById(R.id.top_right);
    }
 
 
    // 为左侧返回按钮添加自定义点击事件
    public void setOnclickLeft(OnClickListener listener) {
        top_left.setOnClickListener(listener);
    }
 
    // 设置标题的方法
    public void setTitle(String title) {
        top_title.setText(title);
    }
 
    // 设置标题的方法
    public void setRightTitle(String title) {
        top_right.setText(title);
    }
}
```

#### 自己绘制控件

熟悉view的绘制原理
1.measure用来测量View的宽和高。
2.layout用来确定View在父容器中放置的位置。
3.draw用来将view绘制在屏幕上

创建一个类CustomView继承View，实现点击事件接口OnClickListener
```java
public class CustomView extends View implements View.OnClickListener {
 
    // 定义画笔
    private Paint mPaint;
    // 用于获取文字的宽和高
    private Rect mRect;
    // 计数值，每点击一次本控件，其值增加1
    private int mCount=0;
 
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        // 初始化画笔、Rect
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        // 本控件的点击事件
        setOnClickListener(this);
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        // 绘制一个填充色为蓝色的矩形
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        String text = String.valueOf(mCount);
        // 获取文字的宽和高
        mPaint.getTextBounds(text, 0, text.length(), mRect);
        float textWidth = mRect.width();
        float textHeight = mRect.height();
 
        // 绘制字符串
        canvas.drawText("点了我"+text+"次", getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);
    }
 
    @Override
    public void onClick(View view) {
        mCount++;
        invalidate();
    }
}
```

#### 继承原生控件

```java
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public final class PhotoImageView extends AppCompatImageView
        implements ScaleGestureDetector.OnScaleGestureListener,
        GestureDetector.OnGestureListener,
        RotateGestureDetector.OnRotateListener {
    private final RotateGestureDetector mRotateGestureDetector;
    private final ScaleGestureDetector mScaleGestureDetector;
    private final GestureDetector mGestureDetector;
    private final Matrix mShowMatrix;


    public PhotoImageView(Context context) {
        this(context, null);
    }

    public PhotoImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRotateGestureDetector = new RotateGestureDetector(this);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        mGestureDetector = new GestureDetector(context, this);
        mShowMatrix = new Matrix();

        super.setScaleType(ScaleType.MATRIX);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        mRotateGestureDetector.onTouchEvent(event);

        mScaleGestureDetector.onTouchEvent(event);


        setImageMatrix(mShowMatrix);
        return true;
    }


    @Override
    public boolean onScale(@NonNull ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();

        if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor))
            return false;

        mShowMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
        return true;
    }

    @Override
    public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(@NonNull ScaleGestureDetector detector) {
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        mShowMatrix.postTranslate(-distanceX, -distanceY);
        setImageMatrix(mShowMatrix);
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        Toast.makeText(getContext(), "longPress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onRotate(float degrees, float focusX, float focusY) {
        mShowMatrix.postRotate(degrees, focusX, focusY);
    }
}
```

在Android开发中，自定义组件可以通过以下三种方式实现：

### 1. 自定义视图 (Custom View)

自定义视图是指从`View`类继承并重写其方法来创建自定义的视图组件。你可以在其中实现自定义的绘制逻辑和事件处理逻辑。

**示例代码**：

```java
public class MyCustomView extends View {
    private Paint paint;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, paint);
    }
}
```

### 2. 自定义视图组 (Custom ViewGroup)

自定义视图组是指从`ViewGroup`类继承并重写其方法来创建自定义的布局容器。可以在其中处理子视图的布局和绘制逻辑。

**示例代码**：

```java
public class MyCustomViewGroup extends ViewGroup {

    public MyCustomViewGroup(Context context) {
        super(context);
    }

    public MyCustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int top = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(0, top, child.getMeasuredWidth(), top + child.getMeasuredHeight());
            top += child.getMeasuredHeight();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            height += child.getMeasuredHeight();
        }

        setMeasuredDimension(width, height);
    }
}
```

### 3. 自定义属性 (Custom Attributes)

自定义属性允许你在XML布局文件中定义和使用属性，并在自定义视图中读取这些属性以进行配置。

**示例代码**：

```xml
<!-- res/values/attrs.xml -->
<resources>
    <declare-styleable name="MyCustomView">
        <attr name="circleColor" format="color"/>
        <attr name="circleRadius" format="dimension"/>
    </declare-styleable>
</resources>
```

```java
public class MyCustomView extends View {
private Paint paint;
private int circleColor;
private float circleRadius;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustomView, 0, 0);

        try {
            circleColor = a.getColor(R.styleable.MyCustomView_circleColor, Color.RED);
            circleRadius = a.getDimension(R.styleable.MyCustomView_circleRadius, 100);
        } finally {
            a.recycle();
        }

        paint = new Paint();
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, paint);
    }
}
```