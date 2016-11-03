package com.badrit.MacAddress;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
// import android.telephony.TelephonyManager;
import android.net.wifi.WifiManager;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * The Class MacAddressPlugin.
 */
public class MacAddressPlugin extends CordovaPlugin {

    public boolean isSynch(String action) {
        if (action.equals("getMacAddress")) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cordova.api.Plugin#execute(java.lang.String,
     * org.json.JSONArray, java.lang.String)
     */
    @Override
    public boolean execute(String action, JSONArray args,
            CallbackContext callbackContext) {

        if (action.equals("getMacAddress")) {

            String macAddress = this.getMacAddress();
//             String imei = this.getImei();

            if (macAddress != null) {
                JSONObject JSONresult = new JSONObject();
                try {
                    JSONresult.put("mac", macAddress);
//                     JSONresult.put("imei",imei);
                    PluginResult r = new PluginResult(PluginResult.Status.OK,
                            JSONresult);
                    callbackContext.success(macAddress);
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                } catch (JSONException jsonEx) {
                    PluginResult r = new PluginResult(
                            PluginResult.Status.JSON_EXCEPTION);
                    callbackContext.error("error");
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the mac address.
     * 
     * @return the mac address
     */
    private String getMacAddress() {
        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {

                NetworkInterface iF = interfaces.nextElement();
                byte[] address = iF.getHardwareAddress();
                int i = iF.getName().indexOf("wlan");
                if (address == null || address.length == 0) {
                    continue;
                }
                StringBuilder macbuf = new StringBuilder();
                StringBuilder mac1buf = new StringBuilder();
                for (byte b : address) {
                    macbuf.append(String.format("%02x", b));
                    mac1buf.append(String.format("%02x:", b));
                }
                if (mac1buf.length() > 0) {
                    mac1buf.deleteCharAt(mac1buf.length() - 1);
                }
                if (i != -1) {
                    return mac1buf.toString();
                }
            }
        }catch (Exception e){
            return "11:11:11:11:11:11";
        }
        return "00:00:00:00:00:00";
    }
     /**
     * Gets the imei.
     * 
     * @return the imei
     */
//    private String getImei() {
//          try{
//                 TelephonyManager telephonyManager = (TelephonyManager)  this.cordova.getActivity().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
//                 String imei = telephonyManager.getDeviceId();
//                 if (imei != null) return imei;
//                 else return "0";
//             }catch (Exception e){
//                 return "-1";
//             }
//         return "0";
//    }
}
