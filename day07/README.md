# DAY07

## Android 补间动画

### Android 补间动画的四种类型

在 Android 中，补间动画（Tween Animation）是一种通过对视图（View）进行平移、缩放、旋转
和透明度变化等操作来创建动画效果的方式。补间动画有四种基本类型：

#### 1. Translate Animation（平移动画）
平移动画用于在屏幕上移动视图的位置，可以沿着 X 轴和 Y 轴进行移动。

- **`TranslateAnimation` 构造方法**：

    ```java
    TranslateAnimation translateAnimation = new TranslateAnimation(
        Animation.RELATIVE_TO_SELF, startX,
        Animation.RELATIVE_TO_SELF, endX,
        Animation.RELATIVE_TO_SELF, startY,
        Animation.RELATIVE_TO_SELF, endY);
    ```

- **属性**：
    - `startX` 和 `endX`：表示 X 轴方向的起始和终止位置。
    - `startY` 和 `endY`：表示 Y 轴方向的起始和终止位置。

- **示例**：

    ```java
    TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 200);
    translateAnimation.setDuration(1000);
    view.startAnimation(translateAnimation);
    ```

#### 2. Scale Animation（缩放动画）
缩放动画用于放大或缩小视图的大小，可以沿着 X 轴和 Y 轴进行缩放。

- **`ScaleAnimation` 构造方法**：

    ```java
    ScaleAnimation scaleAnimation = new ScaleAnimation(
        fromXScale, toXScale,
        fromYScale, toYScale,
        Animation.RELATIVE_TO_SELF, pivotX,
        Animation.RELATIVE_TO_SELF, pivotY);
    ```

- **属性**：
    - `fromXScale` 和 `toXScale`：表示 X 轴方向的起始和终止缩放比例。
    - `fromYScale` 和 `toYScale`：表示 Y 轴方向的起始和终止缩放比例。
    - `pivotX` 和 `pivotY`：表示缩放的中心点。

- **示例**：

    ```java
    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 
        Animation.RELATIVE_TO_SELF, 0.5f, 
        Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setDuration(1000);
    view.startAnimation(scaleAnimation);
    ```

#### 3. Rotate Animation（旋转动画）
旋转动画用于围绕视图的某个点进行旋转。

- **`RotateAnimation` 构造方法**：

    ```java
    RotateAnimation rotateAnimation = new RotateAnimation(
        fromDegrees, toDegrees,
        Animation.RELATIVE_TO_SELF, pivotX,
        Animation.RELATIVE_TO_SELF, pivotY);
    ```

- **属性**：
    - `fromDegrees` 和 `toDegrees`：表示旋转的起始和终止角度。
    - `pivotX` 和 `pivotY`：表示旋转的中心点。

- **示例**：

    ```java
    RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f);
    rotateAnimation.setDuration(1000);
    view.startAnimation(rotateAnimation);
    ```

#### 4. Alpha Animation（透明度动画）
透明度动画用于改变视图的透明度（alpha 值），使其逐渐变为完全透明或完全不透明。

- **`AlphaAnimation` 构造方法**：

    ```java
    AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
    ```

- **属性**：
    - `fromAlpha`：起始透明度，取值范围为 0.0（完全透明）到 1.0（完全不透明）。
    - `toAlpha`：终止透明度，取值范围同上。

- **示例**：

    ```java
    AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
    alphaAnimation.setDuration(1000);
    view.startAnimation(alphaAnimation);
    ```

#### 组合使用（AnimationSet）
这四种动画可以组合使用，创建更加复杂的动画效果。

- **`AnimationSet`**：用于将多种动画组合在一起执行。

- **示例**：

    ```java
    AnimationSet animationSet = new AnimationSet(true);
    animationSet.addAnimation(translateAnimation);
    animationSet.addAnimation(scaleAnimation);
    animationSet.addAnimation(rotateAnimation);
    animationSet.addAnimation(alphaAnimation);
    animationSet.setDuration(1000);
    view.startAnimation(animationSet);
    ```

## 插值器（Interpolator）与估值器（Evaluator）

在 Android 动画中，**插值器（Interpolator）** 和 **估值器（Evaluator）** 是控制动画效果和属性变化的关键组件。

### 自带的插值器（Interpolator）

Android 提供了多种内置插值器，用于控制动画的速率和进度：

1. **LinearInterpolator**
  - **描述**: 动画均匀地从开始到结束，没有加速或减速。

2. **AccelerateInterpolator**
  - **描述**: 动画开始时缓慢，结束时加速。

3. **DecelerateInterpolator**
  - **描述**: 动画开始时快速，结束时减速。

4. **AccelerateDecelerateInterpolator**
  - **描述**: 动画开始和结束时都慢，中间加速。

5. **BounceInterpolator**
  - **描述**: 动画结束时有弹跳效果。

6. **OvershootInterpolator**
  - **描述**: 动画超过目标值，然后回到目标值。

7. **AnticipateInterpolator**
  - **描述**: 动画开始时向后退，然后前进到目标值。

8. **AnticipateOvershootInterpolator**
  - **描述**: 动画开始时向后退，然后超过目标值，最后回到目标值。

9. **CycleInterpolator**
  - **描述**: 动画按照正弦曲线循环，提供周期性的动画效果。

### 自带的估值器（Evaluator）

Android 提供了几种内置估值器，用于计算动画过程中属性的值：

1. **FloatEvaluator**
  - **描述**: 对浮点数进行线性插值。

2. **IntEvaluator**
  - **描述**: 对整数进行线性插值。

3. **ArgbEvaluator**
  - **描述**: 对颜色进行线性插值。

### 自定义插值器（Interpolator）

```java
import android.animation.TimeInterpolator;

public class CustomInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        // 自定义插值逻辑，例如使用正弦曲线
        return (float) Math.sin(input * Math.PI);
    }
}
```

### 自定义估值器（Evaluator）
```java
import android.animation.TypeEvaluator;

public class CustomEvaluator implements TypeEvaluator<Float> {
    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        // 自定义插值逻辑，例如线性插值
        return startValue + fraction * (endValue - startValue);
    }
}
```

## Homework实现
- 多加了重播按钮
- 尝试使用StackedToast实现层叠消息，并在TweenAnimSetCodeActivity中使用。