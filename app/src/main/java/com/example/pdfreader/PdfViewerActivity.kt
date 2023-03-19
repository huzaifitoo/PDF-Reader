package com.example.pdfreader

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import java.io.File


class PdfViewerActivity : AppCompatActivity() {

    private lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        pdfView = findViewById(R.id.pdf_view)

        val filePath = intent.getStringExtra(EXTRA_FILE_PATH)
        if (filePath != null) {
            loadPdfFile(filePath)
        } else {
            finish()
        }
    }

    private fun loadPdfFile(filePath: String) {
        val file = File(filePath)
        if (file.exists()) {
            val uri = Uri.fromFile(file)
            pdfView.fromUri(uri)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .scrollHandle(null)
                .load()
        } else {
            finish()
        }
    }

    companion object {
        private const val EXTRA_FILE_PATH = "extra_file_path"

        fun start(context: Context, filePath: String) {
            val intent = Intent(context, PdfViewerActivity::class.java)
            intent.putExtra(EXTRA_FILE_PATH, filePath)
            context.startActivity(intent)
        }
    }
}
