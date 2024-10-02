import React , { useEffect, useRef } from 'react';
import { View, Text, Image, StyleSheet, Animated } from 'react-native';

function IntroScreen() {

    // 애니메이션 값 설정
  const fadeAnim = useRef(new Animated.Value(0)).current; // 초기 투명도 값 0

  useEffect(() => {
    // 애니메이션 실행 (1초 동안 투명도 1로 변화)
    Animated.timing(fadeAnim, {
      toValue: 1, 
      duration: 2000, // 1초 동안 페이드인
      useNativeDriver: true, // 네이티브 드라이버 사용
    }).start();
    console.log('Animation finished');
  }, [fadeAnim]);

  return (
    <View style={styles.container}>
    <Animated.Text style={[styles.welcomeText, { opacity: fadeAnim }]}>
      00님, {'\n'}환영합니다
    </Animated.Text>

    <Animated.Image
      source={require('./assets/images/logo.png')} 
      style={[styles.image, { opacity: fadeAnim }]}
    />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff'
  },
  welcomeText: {
    fontSize: 35,  
    fontWeight: 'bold',
    color: '#000000',  
    lineHeight: 50, 
    marginRight:'35%',
    marginTop:'-40%',
  },
  image: {
    width: 210,  
    height: 75,
    marginTop: 150,  
  },
});

export default IntroScreen;