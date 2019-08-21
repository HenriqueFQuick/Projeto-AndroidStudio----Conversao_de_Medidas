package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_peso.btn_Converter
import kotlinx.android.synthetic.main.activity_peso.radioGroup

class Altura : AppCompatActivity(), View.OnClickListener {

    private var tmp : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_altura)

        radioGroup.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da temperatura para cada opcao apresentada
            tmp = when (radio.id) {
                R.id.op_Foot -> 1
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        btn_Converter.setOnClickListener(this)
        btn_voltar_Alt_Main.setOnClickListener(this)
    }

    override fun onClick(v: View?){

        var temp : Double = edt_Altura.text.toString().toDouble()
        var result : String = ""

        result = when (tmp) {
            1 -> (temp / 30.48).toString()             //Convertendo para Kelvin
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
            }
        }

        when(v?.id){
            R.id.btn_Converter -> txt_Resultado_Altura.text = result
            R.id.btn_voltar_Alt_Main ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
