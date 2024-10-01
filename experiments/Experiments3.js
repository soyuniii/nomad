import React, { useState, useEffect } from 'react';
import { StyleSheet, View } from 'react-native';
import Video from 'react-native-video'; // 비디오 패키지 import

const Experiments3 = () => {
  const [randomVideo, setRandomVideo] = useState(null);

  // 랜덤으로 비디오 파일 선택
  useEffect(() => {
    const videos = [
      require('../assets/videos/logo1.mp4'), // logo1.mp4
      require('../assets/videos/logo2.mp4'), // logo2.mp4
    ];
    
    const randomIndex = Math.floor(Math.random() * videos.length);
    setRandomVideo(videos[randomIndex]); // 랜덤 비디오 선택
  }, []);

  return (
    <View style={styles.container}>
      {randomVideo && (
        <Video
          source={randomVideo}    // 랜덤으로 선택된 비디오 파일
          style={styles.video}     // 비디오 스타일
          resizeMode="contain"     // 비디오 리사이즈 모드
          repeat={true}            // 비디오 반복 재생
          controls={true}          // 비디오 컨트롤러 표시
        />
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  video: {
    width: '100%',
    height: 300,  // 원하는 비디오 크기 설정
  },
});

export default Experiments3;
