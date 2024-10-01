import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';

function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <View style={styles.textContainer}>
        <Text style={styles.title}>
          <Text style={styles.nomad}>nom:ad</Text>
          가 처음이신가요?
        </Text>
        <Text style={styles.subtitle}>신규 회원이시라면, {"\n"}회원가입을 진행해주세요.</Text>
      </View>

      <View style={styles.buttonContainer}>
        {/* 무료 가입하기 버튼 */}
        <TouchableOpacity style={styles.signUpButton} onPress={() => navigation.navigate('SignUp')}>
          <Text style={styles.signUpButtonText}>무료 가입하기</Text>
        </TouchableOpacity>

        {/* 버튼 사이의 선 */}
        <View style={styles.divider} />

        {/* 로그인 버튼 */}
        <TouchableOpacity style={styles.loginButton} onPress={() => navigation.navigate('Experiment2')}>
          <Text style={styles.loginText}>로그인</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-between',
    backgroundColor: '#fff',
    paddingHorizontal: 20,   // 화면 좌우에 일정 패딩을 추가
  },
  textContainer: {
    flex: 2,
    justifyContent: 'center',
    alignItems: 'flex-start',  // 텍스트를 왼쪽으로 정렬
    width: '100%',            // 텍스트 컨테이너가 화면 전체를 차지하도록 설정
    paddingLeft: 10,          // 왼쪽에 약간의 여백 추가
  },
  title: {
    fontSize: 40,          // 텍스트 크기
    fontWeight: 'bold',    // 텍스트 굵기
    marginBottom: 30,      // 아래 여백 (간격을 넓게 조정)
    textAlign: 'left',     // 텍스트 왼쪽 정렬
    color: '#000',         // 텍스트 색상
    lineHeight: 50,        // 줄 간격 추가
  },
  nomad: {
    letterSpacing: 3,      // nom:ad 부분에만 글자 간격 추가
  },
  subtitle: {
    fontSize: 16,
    color: '#555',
    marginBottom: 40,      // 아래 여백 (간격을 넓게 조정)
    textAlign: 'left',     // 텍스트 왼쪽 정렬
    lineHeight: 24,        // 줄 간격 추가
  },
  buttonContainer: {
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'center',
    marginBottom: 30,      // 버튼을 하단에서 일정 간격 띄움
  },
  signUpButton: {
    backgroundColor: '#d3a26e',
    paddingVertical: 15,
    paddingHorizontal: 40,
    borderRadius: 10,
    width: '80%',           // 버튼 길이를 선의 길이와 일치시킴
    marginBottom: 10,       // 버튼 사이 간격을 줄임
  },
  signUpButtonText: {
    color: '#000',  // 검은색 텍스트
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  divider: {
    width: '80%',            // 선 길이를 버튼 길이와 맞춤
    height: 1,               // 선의 두께
    backgroundColor: '#555',  // 선의 색상
    marginVertical: 10,       // 위아래 여백 (간격을 줄임)
  },
  loginButton: {
    paddingVertical: 15,
    paddingHorizontal: 40,
    width: '80%',            // 로그인 버튼 길이를 선과 맞춤
    textAlign: 'center',
  },
  loginText: {
    fontSize: 16,
    color: '#555',
    textAlign: 'center',   // 텍스트 가운데 정렬
  },
});

export default HomeScreen;
