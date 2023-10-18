package com.example.simple_adapter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import com.example.simple_adapter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListViewSimple()
    }

    @SuppressLint("StringFormatInvalid")
    private fun setupListViewSimple() {
        val data = listOf(
            mapOf(
                KEY_TITLE to "First value",
                KEY_DESCRIPTION to "First description"
            ),
            mapOf(
                KEY_TITLE to "Second value",
                KEY_DESCRIPTION to "Second description"
            ),
            mapOf(
                KEY_TITLE to "Third value",
                KEY_DESCRIPTION to "Third description"
            )
        )

        val adapter = SimpleAdapter(
            this, data,
            R.layout.two_text_view_layout,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            intArrayOf(R.id.tvFirst, R.id.tvSecond)
        )
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener {_, _, position, _ ->
            val selectedTitle = data[position][KEY_TITLE]
            val selectedDescription = data[position][KEY_DESCRIPTION]
            val message = "${getString(R.string.item_selected_message)} $selectedDescription"

            val dialog = AlertDialog.Builder(this)
                .setTitle(selectedTitle)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok)) { dialog, which -> }
                .create()
            dialog.show()
        }
    }
    companion object{
        @JvmStatic val KEY_TITLE = "title"
        @JvmStatic val KEY_DESCRIPTION = "description"
    }
}


