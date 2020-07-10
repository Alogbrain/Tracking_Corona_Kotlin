package Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracking_corona.Adapter.DetailCountryAdapter
import com.example.tracking_corona.R
import com.example.tracking_corona.model.CityModel
import com.example.tracking_corona.service.CountriesService
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.*


class ListDetail : Fragment() {
    lateinit var detailCountryAdapter :DetailCountryAdapter
    lateinit var scope: CoroutineScope;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader.start()
        fetchData()
        swipeToRefresh.setOnRefreshListener {
            fetchData()
        }
    }
    private fun fetchData(){
        scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            val DetailVieNamResp = withContext(Dispatchers.IO) { CountriesService.getApi().getDetailVietNam() }
            val TotalVieNamResp = withContext(Dispatchers.IO) { CountriesService.getApi().getCountryVietNam() }
            swipeToRefresh.isRefreshing = false

            tvconfirmed.text = TotalVieNamResp.cases.toString()
            tvactive.text = TotalVieNamResp.active.toString()
            tvrecovered.text = TotalVieNamResp.recovered.toString()
            tvdeceased.text = TotalVieNamResp.deaths.toString()

            var layoutmanager = LinearLayoutManager(context)
            detailCountryAdapter = DetailCountryAdapter(context,DetailVieNamResp)

            rv.layoutManager    = layoutmanager
            rv.adapter          = detailCountryAdapter

            loader.stop()
            loader.setVisibility(View.GONE)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}