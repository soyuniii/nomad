import React from 'react';
import { StyleSheet, View } from 'react-native';
import { WebView } from 'react-native-webview';
import { API_BASE_URL } from '../config/config';

const Experiment1 = () => {
  return (
    <View style={styles.container}>
      <WebView 
        source={{ uri: `${API_BASE_URL}/index.html` }}  // 서버에서 제공하는 지도 HTML
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
