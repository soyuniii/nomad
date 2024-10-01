import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, TouchableWithoutFeedback, Keyboard } from 'react-native';

function SignUpScreen() {
  const [isFocusedNickname, setIsFocusedNickname] = useState(false); 
  const [isFocusedNationality, setIsFocusedNationality] = useState(false); 
  const [isFocusedEmail, setIsFocusedEmail] = useState(false); 
  const [isFocusedPassword, setIsFocusedPassword] = useState(false); 
  const [isFocusedPasswordConfirm, setIsFocusedPasswordConfirm] = useState(false);





  return (
    <TouchableWithoutFeedback onPress={()=>Keyboard.dismiss()}>
    
    <View style={styles.container}>
      <Text style={styles.title}>회원가입</Text>
      
      {/* 닉네임 입력 */}
      <TextInput
        style={[styles.input, isFocusedNickname && styles.focusedInput]}  
        placeholder={isFocusedNickname ? '' : '닉네임'}  
        placeholderTextColor="#888"  
        onFocus={() => setIsFocusedNickname(true)}  
        onBlur={() => setIsFocusedNickname(false)}  
      />

      {/* 국적 선택 */}
      <TextInput
        style={[styles.input, isFocusedNationality && styles.focusedInput]}  
        placeholder={isFocusedNationality ? '' : '국적'}  
        placeholderTextColor="#888"  
        onFocus={() => setIsFocusedNationality(true)}  
        onBlur={() => setIsFocusedNationality(false)}  
      />

      {/* 이메일 입력 */}
      <TextInput
        style={[styles.input, isFocusedEmail && styles.focusedInput]}  
        placeholder={isFocusedEmail ? '' : '이메일'}  
        keyboardType='email-address'
        placeholderTextColor="#888"  
        onFocus={() => setIsFocusedEmail(true)}  
        onBlur={() => setIsFocusedEmail(false)}  
      />

      {/* 비밀번호 입력 */}
      <TextInput
        style={[styles.input, isFocusedPassword && styles.focusedInput]}  
        placeholder={isFocusedPassword ? '' : '패스워드'}  
        secureTextEntry={true}
        placeholderTextColor="#888"  
        onFocus={() => setIsFocusedPassword(true)}  
        onBlur={() => setIsFocusedPassword(false)}  
      />

      {/* 비밀번호 확인 입력 */}
      <TextInput
        style={[styles.input, isFocusedPasswordConfirm && styles.focusedInput]}  
        placeholder={isFocusedPasswordConfirm ? '' : '패스워드 확인'}  
        secureTextEntry={true}
        placeholderTextColor="#888"  
        onFocus={() => setIsFocusedPasswordConfirm(true)}  
        onBlur={() => setIsFocusedPasswordConfirm(false)}  
      />

      {/* 시작하기 버튼 */}
      <TouchableOpacity style={styles.signUpButton}>
        <Text style={styles.signUpButtonText}>시작하기</Text>
      </TouchableOpacity>
    </View>
      
    </TouchableWithoutFeedback>
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
    color: '#000',
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
  focusedInput: {
    fontWeight: 'bold',  // 포커스될 때 텍스트를 볼드로
  }
});

export default SignUpScreen;
