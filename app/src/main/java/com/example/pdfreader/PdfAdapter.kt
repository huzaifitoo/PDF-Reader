package com.example.pdfreader

// PdfAdapter.kt
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class PdfAdapter(private val context: Context, private val pdfFiles: List<File>) :
    RecyclerView.Adapter<PdfAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_pdf, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pdfFile = pdfFiles[position]
        holder.pdfTitle.text = pdfFile.name
        holder.pdfSubtitle.text = "${pdfFile.length() / 1024} KB"
        holder.itemView.setOnClickListener {
            // Launch PdfViewerActivity to open the selected PDF file
            PdfViewerActivity.start(context, pdfFile.absolutePath)
        }
    }

    override fun getItemCount(): Int = pdfFiles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pdfTitle: TextView = itemView.findViewById(R.id.pdf_title)
        val pdfSubtitle: TextView = itemView.findViewById(R.id.pdf_subtitle)
    }
}
