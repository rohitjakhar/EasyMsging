package com.easymsging.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.shreyaspatil.easyupipayment.EasyUpiPayment
import com.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import com.shreyaspatil.easyupipayment.model.TransactionDetails
import kotlinx.android.synthetic.main.activity_donate.*

class MyActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding : MainFragmentBinding
    private lateinit var adapter: NotesListAdapter


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater , container , false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView){
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                    context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)

        }

        viewModel.notesList.observe(viewLifecycleOwner , Observer {
            Log.i("Note Logging" , it.toString())
            adapter = NotesListAdapter(it)
            binding.recyclerView.adapter = adapter

            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        })
        return binding.root
    }

}