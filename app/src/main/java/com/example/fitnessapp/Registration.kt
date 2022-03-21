package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val span = SpannableString("Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработки персональных данных, а также принимаете пользовательское соглашение")
        val clickSpanOne: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }
            override fun onClick(view: View) {
                Toast.makeText(applicationContext,"Политика конфиденциальности", Toast.LENGTH_LONG).show()
            }
        }
        val clickSpanTwo: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }
            override fun onClick(view: View) {
                Toast.makeText(applicationContext, "Пользовательское соглашение", Toast.LENGTH_LONG).show()
            }
        }

        span.setSpan(clickSpanOne, 37, 66, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(clickSpanTwo, span.length - 27, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.agreementView.movementMethod = LinkMovementMethod()
        binding.agreementView.text = span

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}