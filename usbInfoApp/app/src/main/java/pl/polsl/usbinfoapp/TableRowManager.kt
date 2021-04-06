package pl.polsl.usbinfoapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class TableRowManager(view: MainActivity, private var context: Context) {

    private var devicesTable: TableLayout = view.findViewById(R.id.devicesList)

    /**
     * Creates row
     */
    @SuppressLint("SetTextI18n")
    fun createRow(device: UsbDevice){
        val layoutParameters = TableRow.LayoutParams(115, 90)
        val row = TableRow(context)
        val name = TextView(context)
        val pid = TextView(context)
        val vid = TextView(context)
        row.layoutParams = layoutParameters

        customizeTextViewRow(name, layoutParameters, device.manufacturerName?:"NULL")
        customizeTextViewRow(pid, layoutParameters, "0x" + device.productId)
        customizeTextViewRow(vid, layoutParameters ,"0x" + device.vendorId)

        row.addView(name)
        row.addView(pid)
        row.addView(vid)

        devicesTable.addView(row, TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))

        row.setOnClickListener{
            val detailsIntent = Intent(context, DeviceDetailsActivity::class.java)
            detailsIntent.putExtra("usbDevice", device)
            context.startActivity(detailsIntent)
        }
    }

    /**
     * Removes all rows
     */
    fun removeRows(){
        for(i in 1..devicesTable.childCount){
            val view = devicesTable.getChildAt(i)
            if(view is TableRow)
                devicesTable.removeViewAt(i)
        }
    }

    /**
     * Assigns values to a given text field
     * @param textField a TextView field
     * @param parameters cell parameters (width and height)
     * @param textValue cell text value as String
     */
    private fun customizeTextViewRow(textField: TextView, parameters: TableRow.LayoutParams, textValue: String){
        textField.layoutParams = parameters
        textField.text = textValue
        textField.setBackgroundResource(R.drawable.table_row_border)
        textField.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textField.setPadding(0,12,0,0)
    }
}