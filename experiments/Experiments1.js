import React from 'react';
import { StyleSheet, View } from 'react-native';
import { WebView } from 'react-native-webview';

const Experiment1 = () => {
  return (
    <View style={styles.container}>
      <WebView 
        source={{ uri: 'http://192.168.184.237:8080/index.html' }}  // 서버에서 제공하는 지도 HTML
        style={{ flex: 1 }} 
        originWhitelist={['*']}
      /> 
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default Experiment1;
