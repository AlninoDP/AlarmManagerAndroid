package com.alnino.myalarmmanager

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var mListener: DialogDateListener? = null

    /*
    * Fungsi onAttach() hanya sekali dipanggil dalam fragment dan berfungsi
    * untuk mengkaitkan dengan activity pemanggil
    * onDetach() hanya dipanggil sebelum fragmen tidak lagi dikaitkan dengan activity pemanggil.
    * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as DialogDateListener?
    }

    override fun onDetach() {
        super.onDetach()
        if (mListener !=null){
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)


        return DatePickerDialog(activity as Context, this, year, month, date)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        mListener?.onDialogDateSet(tag,year,month,dayOfMonth)
    }


    interface DialogDateListener {
        fun onDialogDateSet (tag: String?, year:Int, month:Int, dayOfMonth: Int)

    }
}