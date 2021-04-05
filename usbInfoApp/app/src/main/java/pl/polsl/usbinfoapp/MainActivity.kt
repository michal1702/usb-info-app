package pl.polsl.usbinfoapp

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var showDevicesButton: Button
    private lateinit var devicesTable: TableLayout
    private var deviceParamsList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setControls()
        addParamsToList()
        showDevicesButton.setOnClickListener {
            printDevices()
        }
    }

    private fun printDevices(){
        removeRows()
        for(device in deviceParamsList){
            addLine(device)
        }
    }

    private fun addParamsToList(){
        val manager = applicationContext.getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList: HashMap<String, UsbDevice> = manager.deviceList

        deviceList.forEach{(name, device)->
            deviceParamsList.add(device.manufacturerName + ";" + device.productId.toString() + ";" + device.vendorId.toString())
        }
    }

    private fun removeRows(){
        for(i in 1..devicesTable.childCount){
            val view = devicesTable.getChildAt(i)
            if(view is TableRow)
                devicesTable.removeViewAt(i)
        }
    }

    private fun addLine(device: String){
        var deviceParams = emptyArray<String>()
        deviceParams = device.split(";").toTypedArray()
        val layoutParameters = TableRow.LayoutParams(115, TableRow.LayoutParams.WRAP_CONTENT)
        val row = TableRow(this)
        row.layoutParams = layoutParameters
        val name = TextView(this)
        name.layoutParams = layoutParameters
        name.setBackgroundResource(R.drawable.table_row_border)
        name.text = deviceParams[0]
        name.textAlignment = View.TEXT_ALIGNMENT_CENTER
        val pid = TextView(this)
        pid.layoutParams = layoutParameters
        pid.setBackgroundResource(R.drawable.table_row_border)
        pid.text = deviceParams[1]
        pid.textAlignment = View.TEXT_ALIGNMENT_CENTER
        val vid = TextView(this)
        vid.layoutParams = layoutParameters
        vid.setBackgroundResource(R.drawable.table_row_border)
        vid.text = deviceParams[2]
        vid.textAlignment = View.TEXT_ALIGNMENT_CENTER

        row.addView(name)
        row.addView(pid)
        row.addView(vid)

        devicesTable.addView(row, TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT))
    }

    private fun setControls(){
        showDevicesButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.showButton)
        devicesTable =findViewById(R.id.devicesList)
    }
}