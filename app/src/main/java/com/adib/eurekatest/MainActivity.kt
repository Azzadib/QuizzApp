package com.adib.eurekatest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCategories: RecyclerView
    private lateinit var tvNoCategory: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCategories = findViewById(R.id.rv_categories)
        tvNoCategory = findViewById(R.id.tv_no_category)

        setupListofDataIntoRecyclerView()
    }

    private  fun setupListofDataIntoRecyclerView() {
        if (getcategoriesList().size > 0) {
            rvCategories.visibility = View.VISIBLE
            tvNoCategory.visibility = View.GONE

            rvCategories.layoutManager = GridLayoutManager(this, 3)
            val categoryAdapter = CategoryAdapter(this, getcategoriesList())
            rvCategories.adapter = categoryAdapter

            categoryAdapter.setOnItemClickCallback(object: CategoryAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Category) {
                    showSelectedCategory(data)
                }
            })
        } else {
            rvCategories.visibility = View.GONE
            tvNoCategory.visibility = View.VISIBLE
        }
    }

    private fun getcategoriesList() : ArrayList<Category> {
        val databaseHandler = DatabaseHandler(this)
        return databaseHandler.viewCategory()
    }

    private fun showSelectedCategory(category: Category) {
        Toast.makeText(this, "Opening " + category.name, Toast.LENGTH_SHORT).show()

        val questionTest = Intent(this@MainActivity, QuestionActivity::class.java)
        questionTest.putExtra(QuestionActivity.SELECTED_CATEGORY_NAME, category.name)
        questionTest.putExtra(QuestionActivity.SELECTED_CATEGORY_ID, category.id)
        startActivity(questionTest)
    }
}