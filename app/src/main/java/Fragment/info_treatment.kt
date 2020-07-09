package Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tracking_corona.*
import kotlinx.android.synthetic.main.fragment_info.*



class info_treatment : Fragment() {
    lateinit var btn_call: Button
    lateinit var btn_sms: Button

    lateinit var btn_guide_student: Button
    lateinit var btn_guide_school: Button
    lateinit var btn_guide_travel: Button
    lateinit var btn_guide_apartment: Button
    lateinit var btn_guide_suspect: Button
    lateinit var btn_guide_workspace: Button
    lateinit var btn_guide_family: Button
    lateinit var btn_guide_service: Button
    private lateinit var browserIntent: Intent
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_call = view?.findViewById<Button>(R.id.btnCall)
        btn_sms = view?.findViewById<Button>(R.id.btnMes)

        btn_guide_student = view?.findViewById<Button>(R.id.btn_guide_student)
        btn_guide_school = view?.findViewById<Button>(R.id.btn_guide_school)
        btn_guide_travel = view?.findViewById<Button>(R.id.btn_guide_travel)
        btn_guide_apartment = view?.findViewById<Button>(R.id.btn_guide_apartment)
        btn_guide_suspect = view?.findViewById<Button>(R.id.btn_guide_suspect)
        btn_guide_workspace = view?.findViewById<Button>(R.id.btn_guide_workspace)
        btn_guide_family = view?.findViewById<Button>(R.id.btn_guide_family)
        btn_guide_service = view?.findViewById<Button>(R.id.btn_guide_service)

        btn_call.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:19009095"))
            startActivity(callIntent)
        }

        btn_sms.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:1900.9095"))
            startActivity(callIntent)
        }


        btn_guide_student.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(getContext(), btn_guide_student)
            popupMenu.menuInflater.inflate(R.menu.drop_down_menu1, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.dropdown_menu1 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_STUDENT_1_URL)
                        }
                    R.id.dropdown_menu2 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_STUDENT_2_URL)
                        }
                }
                true
            })
            popupMenu.show()
        }

        btn_guide_school.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(getContext(), btn_guide_school)
            popupMenu.menuInflater.inflate(R.menu.drop_down_menu_school, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.dropdown_menu_school_1 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_SCHOOL_1_URL)
                        }
                    R.id.dropdown_menu_school_2 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_SCHOOL_2_URL)
                        }
                }
                true
            })
            popupMenu.show()
        }


        btn_guide_travel.setOnClickListener {
            visitActivityBrowser(GUIDE_TRAVEL_URL)
        }

        btn_guide_apartment.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(getContext(), btn_guide_apartment)
            popupMenu.menuInflater.inflate(R.menu.drop_down_menu_aparment, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.dropdown_menu_apartment_1 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_APARTMENT_1_URL)
                        }
                    R.id.dropdown_menu_apartment_2 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_APARTMENT_2_URL)
                        }
                    R.id.dropdown_menu_apartment_3 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_APARTMENT_3_URL)
                        }
                    R.id.dropdown_menu_apartment_4 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_APARTMENT_4_URL)
                        }

                }
                true
            })
            popupMenu.show()
        }

        btn_guide_suspect.setOnClickListener {
            visitActivityBrowser(GUIDE_SUSPECT_URL)
        }

        btn_guide_workspace.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(getContext(), btn_guide_workspace)
            popupMenu.menuInflater.inflate(R.menu.drop_down_menu_workspace, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.dropdown_menu_workspace_1 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_WORKSPACE_1_URL)
                        }
                    R.id.dropdown_menu_workspace_2 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_WORKSPACE_2_URL)
                        }
                    R.id.dropdown_menu_workspace_3 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_WORKSPACE_3_URL)
                        }
                    R.id.dropdown_menu_workspace_4 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_WORKSPACE_4_URL)
                        }

                }
                true
            })
            popupMenu.show()
        }

        btn_guide_family.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(getContext(), btn_guide_family)
            popupMenu.menuInflater.inflate(R.menu.drop_down_menu_family, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.dropdown_menu_family_1 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_FAMILY_1_URL)
                        }
                    R.id.dropdown_menu_family_2 ->
                        context?.let {
                            visitActivityBrowser(GUIDE_FAMILY_2_URL)
                        }
                }
                true
            })
            popupMenu.show()
        }

        btn_guide_service.setOnClickListener {
            visitActivityBrowser(GUIDE_SERVICE_URL)
        }
    }

    private fun visitActivityBrowser(url:String){
        val intent = Intent(context, ActivityBrowser::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}