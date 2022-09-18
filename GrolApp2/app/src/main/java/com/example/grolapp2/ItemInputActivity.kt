package com.example.grolapp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.grolapp2.database.AppDatabase
import com.example.grolapp2.model.Items
import com.example.grolapp2.presenter.ItemPresenter
import com.example.grolapp2.presenter.RegisterPresenter
import com.example.grolapp2.repository.ItemsRepository
import com.example.grolapp2.repository.UsersRepository

class ItemInputActivity : AppCompatActivity() {
    lateinit var itemName: EditText
    lateinit var itemAmount: EditText
    lateinit var konzum: CheckBox
    lateinit var lidl: CheckBox
    lateinit var kaufland: CheckBox
    lateinit var spar: CheckBox

    lateinit var button: Button

    lateinit var button_karta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_input_fragment)

        val appDatabase = AppDatabase.getDatabase(this)
        val repository = ItemsRepository(appDatabase.itemsDao())
        val presenter = ItemPresenter(repository)

        itemName = findViewById<EditText>(R.id.inputItem)
        itemAmount = findViewById<EditText>(R.id.inputNumber)

        konzum = findViewById(R.id.konzumCheck)
        lidl = findViewById(R.id.lidlCheck)
        kaufland = findViewById(R.id.kauflandCheck)
        spar = findViewById(R.id.sparCheck)
        button = findViewById(R.id.inputButton)

        button_karta = findViewById(R.id.map_button)

        button.setOnClickListener {
            var result = StringBuilder()
            result.append("Dodano")
            if (konzum.isChecked) {
                presenter.save(
                    Items(
                        name = itemName.text.toString(),
                        category = "Konzum",
                        number = itemAmount.text.toString()
                    )
                )
            }

            if (lidl.isChecked) {
                presenter.save(
                    Items(
                        name = itemName.text.toString(),
                        category = "Lidl",
                        number = itemAmount.text.toString()
                    )
                )
            }

            if (kaufland.isChecked) {
                presenter.save(
                    Items(
                        name = itemName.text.toString(),
                        category = "Kaufland",
                        number = itemAmount.text.toString()
                    )
                )
            }

            if (spar.isChecked) {
                presenter.save(
                    Items(
                        name = itemName.text.toString(),
                        category = "Spar",
                        number = itemAmount.text.toString()
                    )
                )
            }
            Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
        }

        backOnList()

        button_karta.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun backOnList() {
        val backButton: View = findViewById(R.id.itemListActivity)
        backButton.setOnClickListener {
            val intent = Intent (this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun insertItem() {
        val itemName : View = findViewById(R.id.inputItem)
    }
}