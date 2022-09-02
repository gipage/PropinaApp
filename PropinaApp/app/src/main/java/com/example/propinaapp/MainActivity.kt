package com.example.propinaapp


import android.animation.ArgbEvaluator
import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat

private const val TAG = "MainActivity"
private const val PropinaInicial = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etCantidad:EditText
    private lateinit var seekBarPropina:SeekBar
    private lateinit var tvPorcentajeLabel:TextView
    private lateinit var tvPropinaCantidad:TextView
    private lateinit var tvTotalCantidad:TextView
    private lateinit var tvCalidadPropina:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etCantidad = findViewById(R.id.etCantidad)
        seekBarPropina=findViewById(R.id.seekBarPropina)
        tvPorcentajeLabel=findViewById(R.id.tvPorcentajeLabel)
        tvPropinaCantidad=findViewById(R.id.tvPropinaCantidad)
        tvTotalCantidad=findViewById(R.id.tvTotalCantidad)
        tvCalidadPropina=findViewById(R.id.tvCalidadPropina)
        seekBarPropina.progress= PropinaInicial
        tvPorcentajeLabel.text = "$PropinaInicial%"
        actualizarCalidadPropina(PropinaInicial)
        seekBarPropina.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG,"onProgressChanged $p1")
                tvPorcentajeLabel.text = "$p1%"
                calculoPropinayTotal()
                actualizarCalidadPropina(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        etCantidad.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG,"afterTextChanged $p0")
                calculoPropinayTotal()
            }

        })
    }

    private fun actualizarCalidadPropina(progreso: Int) {
        val calidadprop = when (progreso){
            in 0..9 -> "Pobre"
            in 10..14->"Aceptable"
            in 15..19->"Buena"
            in 20..24->"Genial"
            else ->"Increible"

        }
        tvCalidadPropina.text=calidadprop
        //actualizar el color con interpolacion
        val color = ArgbEvaluator().evaluate(
            progreso.toFloat() / seekBarPropina.max,
                    ContextCompat.getColor(this,R.color.color_worst_tip),
                    ContextCompat.getColor(this,R.color.color_best_tip)
        ) as Int
        tvCalidadPropina.setTextColor(color)
    }

//gino
    private fun calculoPropinayTotal() {
        if(etCantidad.text.isEmpty()) {
            tvPropinaCantidad.text = ""
            tvTotalCantidad.text = ""
            return
        }
        //obtener el valor de la cuenta y el porcentaje de propina
        val cantidad=etCantidad.text.toString().toDouble()
        val porcentajePropina=seekBarPropina.progress                                             
        
        //calcular propina y total
        val propina=cantidad * porcentajePropina / 100
        val total = cantidad + propina

        //actualizar la UI
        tvPropinaCantidad.text = "%.2f".format(propina)
        tvTotalCantidad.text = "%.2f".format(total)
    }
}