package pl.polsl.usbinfoapp

import android.annotation.SuppressLint
import android.hardware.usb.UsbDevice
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeviceDetailsActivity : AppCompatActivity() {
    private var usbDevice: UsbDevice? = null

    private lateinit var deviceClass: TextView
    private lateinit var devicePath: TextView
    private lateinit var vendorID: TextView
    private lateinit var vendorName: TextView
    private lateinit var productName: TextView
    private lateinit var productID: TextView
    private lateinit var version: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_details)

        val detailsIntent = intent
        usbDevice = detailsIntent.getParcelableExtra("usbDevice")
        setTextViews()
        modifyActionBar(usbDevice?.manufacturerName ?: "USB Device")
        assignUSBParameters()
    }

    /**
     * Changes style of action bar
     * @param deviceName name of a device
     */
    private fun modifyActionBar(deviceName: String){
        val actionBar = supportActionBar
        actionBar!!.title = deviceName
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Assigns all usb informations to text views
     */
    @SuppressLint("SetTextI18n")
    private fun assignUSBParameters(){
        deviceClass.text = usbDevice?.deviceClass.toString()
        devicePath.text = usbDevice?.deviceName
        vendorID.text = "0x" + usbDevice?.vendorId
        vendorName.text = usbDevice?.manufacturerName
        productID.text = "0x" + usbDevice?.productId
        productName.text = usbDevice?.productName
        version.text = usbDevice?.version
    }

    /**
     * Sets up text views
     */
    private fun setTextViews(){
        deviceClass = findViewById(R.id.deviceClassTextValue)
        devicePath = findViewById(R.id.devicePathTextValue)
        vendorID = findViewById(R.id.vendorIDTextValue)
        vendorName = findViewById(R.id.vendorNameTextValue)
        productID = findViewById(R.id.productIDTextValue)
        productName = findViewById(R.id.productNameTextValue)
        version = findViewById(R.id.versionTextValue)
    }
}