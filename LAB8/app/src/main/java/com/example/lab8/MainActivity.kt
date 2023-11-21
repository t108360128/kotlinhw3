package com.example.lab8
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private val contacts = ArrayList<Contact>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let { extras ->
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                val name = extras.getString("name")
                val photo = extras.getString("phone")
                if (name != null && photo != null) {
                    contacts.add(Contact(name, photo))
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val btnAdd: Button = findViewById(R.id.btn_add)

        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recyclerView.layoutManager = linearLayoutManager

        adapter = MyAdapter(contacts).also {
            recyclerView.adapter = it
        }

        btnAdd.setOnClickListener {
            startActivityForResult(Intent(this, SecActivity::class.java), 1)
        }
    }
}

//設計新的類別定義聯絡人的資料結構
data class Contact (
    val name: String, //姓名
    val phone: String //電話
)