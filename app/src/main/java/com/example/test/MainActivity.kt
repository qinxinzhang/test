package com.example.test

import android.R.attr.button
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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

        val arrowAnimation = TranslateAnimation(0f,10f,0f,0f)
        arrowAnimation.duration = 500
        arrowAnimation.repeatCount = 6
        arrowAnimation.repeatMode = Animation.REVERSE


        semiCircle.setOnClickListener {
            view.startAnimation(animShow)

        }
        ivClose.setOnClickListener {
            view.startAnimation(animHide)
        }

        animShow.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                semiCircle.isVisible = false
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
                semiCircle.isVisible = true
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