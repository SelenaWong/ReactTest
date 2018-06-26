import React, {Component } from 'react';
import{
      AppRegistry,
      StyleSheet,
      Text,
      View,
}from 'react-native';

 class TestReact extends React.Component{

    render(){
        return(
             <View style={styles.container}>
                    <Text style ={styles.hello}>Test React</Text>
                </View>
        );
    }
}

var styles = StyleSheet.create({
    container:{
        flex:1,
        justifyContent: 'center',
    },
    hello: {
        fontSize:20,
        textAlign: 'center',
        margin:10,
    },
});

AppRegistry.registerComponent('MyReactNativeApp',()=>TestReact);

