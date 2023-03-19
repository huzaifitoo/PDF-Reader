package com.example.pdfreader.Fragments

import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfreader.PdfAdapter
import com.example.pdfreader.databinding.FragmentHomeBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pdfFiles: MutableList<File>
    private lateinit var pdfRecyclerView: RecyclerView
    private lateinit var pdfAdapter: PdfAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        pdfFiles = mutableListOf()
        //pdfRecyclerView = findViewById(R.id.pdf_recycler_view)


        // Request external storage permission using Dexter
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    // Permission granted, load the PDF files
                    loadPdfFiles()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    // Permission denied, show a message to the user
                    // You can use a Toast or a Snackbar here
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: com.karumi.dexter.listener.PermissionRequest?,
                    token: PermissionToken?
                ) {
                    // Show a rationale to the user and ask for permission again
                    if (token != null) {
                        token.continuePermissionRequest()
                    }
                }
            })
            .check()

        return binding.root
    }

    private fun loadPdfFiles() {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/Download"
        Log.d("PDFVIEWER", "Path: $path")

        // Replace with the actual path to the phone memory

        val directory = File(path)
        if (!directory.exists()) {
            // Show a message to the user indicating that the directory doesn't exist
            return
        }

        pdfFiles = directory.listFiles { file ->
            file.isFile && file.name.endsWith(".pdf")
        }.toMutableList()

        pdfAdapter = PdfAdapter(requireContext(), pdfFiles)
       binding.pdfRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pdfRecyclerView.adapter = pdfAdapter


    }
}
