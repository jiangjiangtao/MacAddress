//
//  MacAddressPlugin.m
//  MacAddressPlugin
//
//  Created by Admin on 04/04/13.
//
//

#import "MacAddressPlugin.h"
#import <AdSupport/AdSupport.h>
#include <sys/socket.h>
#include <sys/sysctl.h>
#include <net/if.h>
#include <net/if_dl.h>

@implementation MacAddressPlugin

- (void)getMacAddress:(CDVInvokedUrlCommand*)command {
    
    CDVPluginResult* pluginResult = nil;
      
    NSString *macAddressString = [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:macAddressString];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    
}

@end
