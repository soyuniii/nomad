import React, { useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import Video from 'react-native-video';

const SplashScreenComponent = ({ navigation }) => {
  const [randomVideo, setRandomVideo] = useState(null);

  // 컴포넌트가 마운트될 때 랜덤으로 비디오 파일 선택
  useEffect(() => {
    const videos = [
      require('./assets/videos/logo1.mp4'),
      require('./assets/videos/logo2.mp4'),
    ];
    
    // 랜덤으로 비디오 선택
    const randomIndex = Math.floor(Math.random() * videos.length);
    setRandomVideo(videos[randomIndex]);
  }, []);

  // 비디오가 끝나면 메인 화면으로 이동
  const handleVideoEnd = () => {
    navigation.replace('Home');  // 비디오 재생이 끝나면 메인 화면(HomeScreen)으로 이동
  };

  return (
    <View style={styles.container}>
      {randomVideo && (
        <Video
          source={randomVideo}  // 랜덤으로 선택된 비디오 파일
          style={styles.video}
          resizeMode="contain"  // 비디오 크기를 화면에 맞춤
          onEnd={handleVideoEnd}  // 비디오가 끝나면 handleVideoEnd 실행
          repeat={false}  // 비디오 반복 재생하지 않음
        />
      )}
    </View>
  );
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',  // 배경을 흰색(#fff)으로 설정
      justifyContent: 'center',
      alignItems: 'center',
    },
    video: {
      width: '100%',
      height: '100%',
    },
  });

export default SplashScreenComponent;
