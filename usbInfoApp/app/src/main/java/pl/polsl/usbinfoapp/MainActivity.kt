package pl.polsl.usbinfoapp

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var showDevicesButton: Button
    private var tableManager: TableRowManager? = null
    private var deviceParamsList = arrayListOf<UsbDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableManager = TableRowManager(this, applicationContext)
        setControls()
        addParamsToList()
        showDevicesButton.setOnClickListener {
            printDevices()
        }
    }

    /**
     * Method is responsible for printing all connected devices
     */
    private fun printDevices(){
        tableManager?.removeRows()
        for(device in deviceParamsList){
            tableManager?.createRow(device)
        }
    }

    /**
     * Adds parameters of USB device to a table view
     */
    private fun addParamsToList(){
        val manager = applicationContext.getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList: HashMap<String, UsbDevice> = manager.deviceList

        deviceList.forEach{(name, device)->
            deviceParamsList.add(device)
        }
    }

    /**
     * Sets up controls
     */
    private fun setControls(){
        showDevicesButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.showButton)
    }
}