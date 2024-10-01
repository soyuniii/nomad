// SignUpScreen.js
import React from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';

function SignUpScreen() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>회원가입</Text>
      
      {/* 닉네임 입력 */}
      <TextInput style={styles.input} placeholder="닉네임 *" />
      
      {/* 국적 선택 */}
      <TextInput style={styles.input} placeholder="국적" />
      
      {/* 이메일 입력 */}
      <TextInput style={styles.input} placeholder="이메일 *" keyboardType="email-address" />
      
      {/* 비밀번호 입력 */}
      <TextInput style={styles.input} placeholder="패스워드 *" secureTextEntry={true} />
      
      {/* 비밀번호 확인 입력 */}
      <TextInput style={styles.input} placeholder="패스워드 확인 *" secureTextEntry={true} />

      {/* 시작하기 버튼 */}
      <TouchableOpacity style={styles.signUpButton}>
        <Text style={styles.signUpButtonText}>시작하기</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'center',
  },
  input: {
    height: 50,
    borderColor: '#ddd',
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 15,
  },
  signUpButton: {
    backgroundColor: '#d3a26e',
    paddingVertical: 15,
    paddingHorizontal: 40,
    borderRadius: 10,
    alignItems: 'center',
  },
  signUpButtonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
});

export default SignUpScreen;
