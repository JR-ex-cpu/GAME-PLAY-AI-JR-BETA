package com.jr.gameplayai

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var status: TextView
    private lateinit var log: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pad = (16 * resources.displayMetrics.density).toInt()
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad, pad, pad)
        }

        val title = TextView(this).apply {
            text = "GAME PLAY AI JR — BETA"
            textSize = 20f
            setTypeface(typeface, Typeface.BOLD)
        }
        status = TextView(this).apply { textSize = 16f; setPadding(0, pad, 0, pad) }

        val openSettings = Button(this).apply {
            text = "Activar accesibilidad"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }
        val refresh = Button(this).apply {
            text = "Actualizar eventos"
            setOnClickListener { render() }
        }
        val stop = Button(this).apply {
            text = "PARAR servicio"
            setOnClickListener {
                GamePlayAccessibilityService.instance?.disableSelf()
                GamePlayAccessibilityService.events.clear()
                render()
            }
        }

        log = TextView(this).apply { textSize = 12f }
        val scroll = ScrollView(this).apply { addView(log) }

        root.addView(title)
        root.addView(status)
        root.addView(openSettings)
        root.addView(refresh)
        root.addView(stop)
        root.addView(scroll)
        setContentView(root)
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    private fun render() {
        val active = GamePlayAccessibilityService.instance != null
        status.text = if (active) "Servicio: ACTIVO" else "Servicio: INACTIVO"
        log.text = GamePlayAccessibilityService.events.joinToString("\n\n")
    }
}
