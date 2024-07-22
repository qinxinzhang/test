package com.example.test

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import androidx.core.view.isInvisible
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<View>(R.id.cl_anim)
        val semiCircle = findViewById<ConstraintLayout>(R.id.cl_semi_circle)
//        val ivCircle = findViewById<ImageView>(R.id.iv_circle)

        val ivClose = view.findViewById<ImageView>(R.id.iv_close)


        val ivArrow = semiCircle.findViewById<ImageView>(R.id.iv_arrow)

        val animShow: Animation = AnimationUtils.loadAnimation(this, R.anim.dialog_in)
        val animHide: Animation = AnimationUtils.loadAnimation(this, R.anim.dialog_out)

        val arrowAnimation = TranslateAnimation(0f, 10f, 0f, 0f)
        arrowAnimation.duration = 500
        arrowAnimation.repeatCount = 6
        arrowAnimation.repeatMode = Animation.REVERSE

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        val screenWidth = displayMetrics.widthPixels

        val startPos = resources.getDimension(R.dimen.start_pos)
        val width = resources.getDimension(R.dimen.view_width)
        val translation = resources.getDimension(R.dimen.move_x)


//        view.setOnClickListener {
//            val propertyX = PropertyValuesHolder.ofFloat(View.X, 0f)
//            val propertyY = PropertyValuesHolder.ofFloat(View.Y, 0f)
//            ObjectAnimator.ofPropertyValuesHolder(view, propertyX, propertyY).apply {
//                duration = 2000L
//                start()
//            }
//        }
        val expand = ObjectAnimator.ofFloat(view, "translationX", 0f)
        val fold = ObjectAnimator.ofFloat(view, "translationX", startPos)
        fold.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {

            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })
        expand.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                Log.d("position",  view.translationX.toString())
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })
        expand.duration = 500
        view.setOnClickListener {
            expand.start()
        }

        ivClose.setOnClickListener {
            fold.start()
        }

        animShow.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
//                semiCircle.isVisible = false
                view.isVisible = true

            }

            override fun onAnimationEnd(animation: Animation) {
                view.clearAnimation()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        animHide.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                view.isInvisible = true
                ivArrow.startAnimation(arrowAnimation)
                view.clearAnimation()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })


        arrowAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                ivClose.clearAnimation()

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}