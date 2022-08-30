package com.example.propinaapp


import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

private const val TAG = "MainActivity"
private const val PropinaInicial = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etCantidad:EditText
    private lateinit var seekBarPropina:SeekBar
    private lateinit var tvPorcentajeLabel:TextView
    private lateinit var tvPropinaCantidad:TextView
    private lateinit var tvTotalCantidad:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etCantidad = findViewById(R.id.etCantidad)
        seekBarPropina=findViewById(R.id.seekBarPropina)
        tvPorcentajeLabel=findViewById(R.id.tvPorcentajeLabel)
        tvPropinaCantidad=findViewById(R.id.tvPropinaCantidad)
        tvTotalCantidad=findViewById(R.id.tvTotalCantidad)
        seekBarPropina.progress= PropinaInicial
        tvPorcentajeLabel.text = "$PropinaInicial%"

        seekBarPropina.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG,"onProgressChanged $p1")
                tvPorcentajeLabel.text = "$p1%"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
}