package ru.example.myfirstkotlinapp.getApi

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.TextView
import ru.example.myfirstkotlinapp.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.URL
import java.nio.ByteBuffer
import java.nio.ByteOrder

object getApi {
   fun apipipi(context: Context):String{
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo = wifiManager.getConnectionInfo()
        val ipInt = wifiInfo.getIpAddress()
        val ip = InetAddress.getByAddress(
            ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array())
            .getHostAddress()

return ip
    }


fun getipFromaws(): String{
            val whatismyip = URL("https://myexternalip.com/raw")
        val input = BufferedReader(
            InputStreamReader(
                whatismyip.openStream()
            )
        )
        val ip: String = input.readLine()
    Log.i("getAip", ip)//you get the IP as a String
    return ip
}


    fun getIpv4HostAddress(): String {
        NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
            networkInterface.inetAddresses?.toList()?.find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let { return it.hostAddress }
        }
        return ""
    }
}