package pl.polsl.usbinfoapp

import android.hardware.usb.UsbDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DeviceDetailsActivity : AppCompatActivity() {
    private var usbDevice: UsbDevice? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_details)
        val detailsIntent = intent
        usbDevice = detailsIntent.getParcelableExtra("usbDevice")
    }
}