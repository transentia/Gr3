package gr3

import groovy.beans.Bindable

@Bindable class PrefsPanelModel {
  String server = PrefsUtils.getServer()
  String port = PrefsUtils.getPort()
//  Boolean serverTfValid = true
//  Boolean portTfValid = true
}
