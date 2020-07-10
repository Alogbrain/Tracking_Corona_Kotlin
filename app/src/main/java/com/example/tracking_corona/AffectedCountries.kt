package com.example.tracking_corona

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tracking_corona.Adapter.CountriesAdapter
import com.example.tracking_corona.model.Countries
import com.example.tracking_corona.model.CountryModel
import com.example.tracking_corona.service.CountriesService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_affected_countries.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException

class AffectedCountries : AppCompatActivity(){
    companion object {
        var countryModelsList: MutableList<CountryModel> = ArrayList<CountryModel>();
    }
    var countryModel: CountryModel? = null
    var myCustomAdapter: CountriesAdapter? = null
    lateinit var scope: CoroutineScope;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected_countries)

        loader.start()
        fetchData();


        supportActionBar?.setTitle("Các quốc gia ảnh hưởng")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                startActivity(
                    Intent(
                        applicationContext,
                        DetailActivity::class.java
                    ).putExtra("position", position)
                )
            }

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                myCustomAdapter!!.filter.filter(s)
                myCustomAdapter!!.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
    private fun fetchData() {

        scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            val CountriesResp = withContext(Dispatchers.IO) {
                CountriesService.getApi().getCountries()
            }
            countryModelsList.addAll(CountriesResp)

            myCustomAdapter = CountriesAdapter(this@AffectedCountries, countryModelsList)
            listView.setAdapter(myCustomAdapter)
            loader.stop()
            loader.setVisibility(View.GONE)
        }
    }
}
