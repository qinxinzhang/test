package com.example.test

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView


class MainActivity : AppCompatActivity() {
    var isTotalExpanded = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView = findViewById<NestedScrollView>(R.id.main)
        val view = findViewById<View>(R.id.cl_anim)
        val semiCircle = findViewById<ConstraintLayout>(R.id.cl_semi_circle)

        val ivCircle = view.findViewById<ImageView>(R.id.iv_1)
        val ivClose = view.findViewById<ImageView>(R.id.iv_close)

        val ivArrow = semiCircle.findViewById<ImageView>(R.id.iv_arrow)

        val arrowAnimation = TranslateAnimation(0f, -20f, 0f, 0f)
        arrowAnimation.duration = 500
        arrowAnimation.repeatCount = 4
        arrowAnimation.repeatMode = Animation.REVERSE

        val startPos = resources.getDimension(R.dimen.start_pos)
        val halfCircle = resources.getDimension(R.dimen.move_x_half)
        val width = resources.getDimension(R.dimen.view_width)
        val translation = resources.getDimension(R.dimen.move_x)

        val expand = ObjectAnimator.ofFloat(view, "translationX", 0f)
        val fold = ObjectAnimator.ofFloat(view, "translationX", startPos)

        val expandHalfCircle = ObjectAnimator.ofFloat(view, "translationX", halfCircle)
        expand.duration = 500
        fold.duration = 500
        expandHalfCircle.duration = 200

        val scaleX = ObjectAnimator.ofFloat(ivCircle, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(ivCircle, "scaleY", 0f, 1f)

        scaleX.setDuration(200)
        scaleY.setDuration(200)


        view.setOnClickListener {
            expand.start()
        }

        ivClose.setOnClickListener {
            fold.start()
        }
        rootView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d("position", scaleX.toString())
            if (isTotalExpanded) {
                fold.start()
            }
        }
        fold.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {
                ivArrow.startAnimation(arrowAnimation)
                semiCircle.isVisible = true
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })
        expand.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
                semiCircle.isVisible = false
            }

            override fun onAnimationEnd(p0: Animator) {
                isTotalExpanded = true
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })
        expandHalfCircle.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                semiCircle.isVisible = false
                scaleX.start()
                scaleY.start()
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })


        arrowAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                ivArrow.clearAnimation()
                expandHalfCircle.start()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}