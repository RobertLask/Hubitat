#!include:getHeaderLicense()

#!include:getDefaultImports()

metadata {
	definition (name: "Tasmota - S120 Plug", namespace: "tasmota", author: "Markus Liljergren", vid:"generic-switch") {
        capability "Actuator"
		capability "Switch"
		capability "Sensor"

        #!include:getDefaultMetadataCapabilities()
        
        #!include:getDefaultMetadataAttributes()
        #!include:getDefaultMetadataCommands()
	}

	simulator {
	}
    
    preferences {
        #!include:getDefaultMetadataPreferences()
        #!include:getDefaultMetadataPreferencesForTasmota(False) # False = No TelePeriod setting, True is default
	}
}

#!include:getDeviceInfoFunction()

#!include:getGenericOnOffFunctions()

/* These functions are unique to each driver */
def parse(description) {
    #!include:getGenericTasmotaParseHeader()
            #!include:getTasmotaParserForBasicData()
            #!include:getTasmotaParserForWifi()
        #!include:getGenericTasmotaParseFooter()
}

def update_needed_settings()
{
    #!include:getUpdateNeededSettingsTasmotaHeader()

    #!include:getUpdateNeededSettingsTasmotaDynamicModuleCommand(0, '{"NAME":"S120 Plug","GPIO":[0,0,0,0,0,21,0,0,0,52,90,0,0],"FLAG":0,"BASE":18}')

    //Disabling these here, but leacing them if anyone needs them
    //cmds << getAction(getCommandString("SetOption81", "1")) // Set PCF8574 component behavior for all ports as inverted
    //cmds << getAction(getCommandString("LedPower", "1"))  // 1 = turn LED ON and set LedState 8
    //mds << getAction(getCommandString("LedState", "8"))  // 8 = LED on when Wi-Fi and MQTT are connected.
    
    #!include:getUpdateNeededSettingsTelePeriod()
    
    #!include:getUpdateNeededSettingsTasmotaFooter()
}

#!include:getDefaultFunctions()

#!include:getLoggingFunction()

#!include:getHelperFunctions('default')

#!include:getHelperFunctions('tasmota')