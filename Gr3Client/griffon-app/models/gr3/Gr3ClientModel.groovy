package gr3

import groovy.beans.Bindable

@Bindable class Gr3ClientModel {
  String totalizerValue = '$0'
  String earnings = '$0'
  String message = 'Good Luck!'
  String playedValue = '$0'
  Integer plays = 0
  Boolean playButtonEnabled = true
}
