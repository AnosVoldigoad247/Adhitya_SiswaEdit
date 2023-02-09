package com.example.adhitya_siswaedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var inputNis : EditText
    private lateinit var inputNama : EditText
    private lateinit var lakilaki : RadioButton
    private lateinit var perempuan : RadioButton
    private lateinit var tambahData : Button
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNis = findViewById(R.id.input_nis)
        inputNama = findViewById(R.id.input_nama)
        lakilaki = findViewById(R.id.rb_lakiLaki)
        perempuan = findViewById(R.id.rb_perempuan)
        tambahData = findViewById(R.id.btn_tambah)
        recyclerView = findViewById(R.id.listData)

        val data = mutableListOf<DataSiswa>()
        viewManager = LinearLayoutManager(this)
        recyclerAdapter = AdapterSiswa(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager

        tambahData.setOnClickListener {
            val nis = inputNis.text.toString()
            val nama = inputNama.text.toString()
            var jk = ""
            if (lakilaki.isChecked){
                jk = "Laki-Laki"
            } else {
                jk = "Perempuan"
            }

            val dataSiswa = DataSiswa(nis,nama,jk)
            data.add(dataSiswa)
            recyclerAdapter.notifyDataSetChanged()
        }
    }
}