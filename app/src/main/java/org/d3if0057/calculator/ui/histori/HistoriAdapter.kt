package org.d3if0057.calculator.ui.histori

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0057.calculator.databinding.ItemHistoriBinding
import org.d3if0057.calculator.db.KalkulatorEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<KalkulatorEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<KalkulatorEntity>() {
                override fun areItemsTheSame(
                    oldData: KalkulatorEntity, newData: KalkulatorEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: KalkulatorEntity, newData: KalkulatorEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        private lateinit var historiViewModel: HistoriViewModel

        fun bind(item: KalkulatorEntity) = with(binding) {
            val dataInput = item.input
            input.text = dataInput
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            val dataOutput = item.output
            output.text = dataOutput
            root.setOnClickListener {
                val builder = AlertDialog.Builder(root.context)
                builder.setTitle("Hapus Item")
                builder.setMessage("Apakah anda ingin menghapus histori ini?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(root.context,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(root.context,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }

                builder.show()
            }
        }


    }
}