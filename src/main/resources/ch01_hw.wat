(module
  (type (;0;) (func (param i32)))
  (type (;1;) (func))
  (type (;2;) (func (param i32 i32) (result i32)))
  (type (;3;) (func (param i32) (result i32)))
  (type (;4;) (func (param i32 i32 i32)))
  (import "env" "print_char" (func $print_char (type 0)))
  (func $main (type 1)
    (local i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32)
    global.get 0
    local.set 0
    i32.const 32
    local.set 1
    local.get 0
    local.get 1
    i32.sub
    local.set 2
    local.get 2
    global.set 0
    i32.const 1048576
    local.set 3
    local.get 2
    local.get 3
    i32.store offset=24
    i32.const 14
    local.set 4
    local.get 2
    local.get 4
    i32.store offset=28
    local.get 2
    i32.load offset=24
    local.set 5
    local.get 2
    i32.load offset=28
    local.set 6
    local.get 2
    local.get 5
    local.get 6
    call $_ZN4core5slice87_$LT$impl$u20$core..iter..traits..collect..IntoIterator$u20$for$u20$$RF$$u5b$T$u5d$$GT$9into_iter17h662a6b4ca30f6fdaE
    local.get 2
    i32.load offset=4 align=1
    local.set 7
    local.get 2
    i32.load align=1
    local.set 8
    local.get 2
    local.get 8
    i32.store offset=8
    local.get 2
    local.get 7
    i32.store offset=12
    loop  ;; label = @1
      i32.const 8
      local.set 9
      local.get 2
      local.get 9
      i32.add
      local.set 10
      local.get 10
      local.set 11
      local.get 11
      call $_ZN85_$LT$core..slice..Iter$LT$T$GT$$u20$as$u20$core..iter..traits..iterator..Iterator$GT$4next17h1023e5bd6414a678E
      local.set 12
      local.get 2
      local.get 12
      i32.store offset=20
      local.get 2
      i32.load offset=20
      local.set 13
      i32.const 0
      local.set 14
      local.get 13
      local.get 14
      i32.ne
      local.set 15
      block  ;; label = @2
        block  ;; label = @3
          local.get 15
          br_table 0 (;@3;) 1 (;@2;) 0 (;@3;)
        end
        i32.const 32
        local.set 16
        local.get 2
        local.get 16
        i32.add
        local.set 17
        local.get 17
        global.set 0
        return
      end
      local.get 2
      i32.load offset=20
      local.set 18
      local.get 18
      i32.load8_u
      local.set 19
      i32.const 255
      local.set 20
      local.get 19
      local.get 20
      i32.and
      local.set 21
      local.get 21
      call $print_char
      br 0 (;@1;)
    end
    unreachable)
  (func $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$12wrapping_add17h804b98cc596b4beaE (type 2) (param i32 i32) (result i32)
    (local i32)
    local.get 0
    local.get 1
    call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$15wrapping_offset17h15f931fc13aca864E
    local.set 2
    local.get 2
    return)
  (func $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$15wrapping_offset17h15f931fc13aca864E (type 2) (param i32 i32) (result i32)
    (local i32 i32 i32 i32 i32)
    global.get 0
    local.set 2
    i32.const 16
    local.set 3
    local.get 2
    local.get 3
    i32.sub
    local.set 4
    local.get 0
    local.get 1
    i32.add
    local.set 5
    local.get 4
    local.get 5
    i32.store offset=12
    local.get 4
    i32.load offset=12
    local.set 6
    local.get 6
    return)
  (func $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$3add17h2957673ffa7fb5dcE (type 2) (param i32 i32) (result i32)
    (local i32)
    local.get 0
    local.get 1
    call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$6offset17h9773484c37f3cdc3E
    local.set 2
    local.get 2
    return)
  (func $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$6offset17h9773484c37f3cdc3E (type 2) (param i32 i32) (result i32)
    (local i32 i32 i32 i32 i32)
    global.get 0
    local.set 2
    i32.const 16
    local.set 3
    local.get 2
    local.get 3
    i32.sub
    local.set 4
    local.get 0
    local.get 1
    i32.add
    local.set 5
    local.get 4
    local.get 5
    i32.store offset=12
    local.get 4
    i32.load offset=12
    local.set 6
    local.get 6
    return)
  (func $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$7is_null17hab6a48c9c6634120E (type 3) (param i32) (result i32)
    (local i32 i32 i32 i32 i32 i32)
    i32.const 0
    local.set 1
    local.get 0
    local.set 2
    local.get 1
    local.set 3
    local.get 2
    local.get 3
    i32.eq
    local.set 4
    i32.const 1
    local.set 5
    local.get 4
    local.get 5
    i32.and
    local.set 6
    local.get 6
    return)
  (func $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$3len17h7d755ec5a1795ddbE (type 2) (param i32 i32) (result i32)
    (local i32 i32 i32 i32)
    global.get 0
    local.set 2
    i32.const 16
    local.set 3
    local.get 2
    local.get 3
    i32.sub
    local.set 4
    local.get 4
    local.get 0
    i32.store offset=8
    local.get 4
    local.get 1
    i32.store offset=12
    local.get 4
    i32.load offset=12
    local.set 5
    local.get 5
    return)
  (func $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$4iter17h90193f7fe9cb8be6E (type 4) (param i32 i32 i32)
    (local i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32)
    global.get 0
    local.set 3
    i32.const 16
    local.set 4
    local.get 3
    local.get 4
    i32.sub
    local.set 5
    local.get 5
    global.set 0
    local.get 1
    local.get 2
    call $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$6as_ptr17hcf74385c24d97857E
    local.set 6
    local.get 6
    call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$7is_null17hab6a48c9c6634120E
    drop
    i32.const 1
    local.set 7
    local.get 5
    local.get 7
    i32.store offset=12
    local.get 5
    i32.load offset=12
    local.set 8
    block  ;; label = @1
      block  ;; label = @2
        block  ;; label = @3
          local.get 8
          i32.eqz
          br_if 0 (;@3;)
          local.get 1
          local.get 2
          call $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$3len17h7d755ec5a1795ddbE
          local.set 9
          br 1 (;@2;)
        end
        local.get 1
        local.get 2
        call $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$3len17h7d755ec5a1795ddbE
        local.set 10
        local.get 6
        local.get 10
        call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$12wrapping_add17h804b98cc596b4beaE
        local.set 11
        local.get 5
        local.get 11
        i32.store offset=8
        br 1 (;@1;)
      end
      local.get 6
      local.get 9
      call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$3add17h2957673ffa7fb5dcE
      local.set 12
      local.get 5
      local.get 12
      i32.store offset=8
    end
    local.get 5
    i32.load offset=8
    local.set 13
    local.get 5
    local.get 6
    i32.store
    local.get 5
    local.get 13
    i32.store offset=4
    local.get 5
    i32.load
    local.set 14
    local.get 5
    i32.load offset=4
    local.set 15
    local.get 0
    local.get 15
    i32.store offset=4
    local.get 0
    local.get 14
    i32.store
    i32.const 16
    local.set 16
    local.get 5
    local.get 16
    i32.add
    local.set 17
    local.get 17
    global.set 0
    return)
  (func $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$6as_ptr17hcf74385c24d97857E (type 2) (param i32 i32) (result i32)
    local.get 0
    return)
  (func $_ZN4core5slice87_$LT$impl$u20$core..iter..traits..collect..IntoIterator$u20$for$u20$$RF$$u5b$T$u5d$$GT$9into_iter17h662a6b4ca30f6fdaE (type 4) (param i32 i32 i32)
    (local i32 i32 i32 i32 i32 i32 i32 i32 i32)
    global.get 0
    local.set 3
    i32.const 16
    local.set 4
    local.get 3
    local.get 4
    i32.sub
    local.set 5
    local.get 5
    global.set 0
    i32.const 8
    local.set 6
    local.get 5
    local.get 6
    i32.add
    local.set 7
    local.get 7
    local.get 1
    local.get 2
    call $_ZN4core5slice29_$LT$impl$u20$$u5b$T$u5d$$GT$4iter17h90193f7fe9cb8be6E
    local.get 5
    i32.load offset=12 align=1
    local.set 8
    local.get 5
    i32.load offset=8 align=1
    local.set 9
    local.get 0
    local.get 8
    i32.store offset=4
    local.get 0
    local.get 9
    i32.store
    i32.const 16
    local.set 10
    local.get 5
    local.get 10
    i32.add
    local.set 11
    local.get 11
    global.set 0
    return)
  (func $_ZN85_$LT$core..slice..Iter$LT$T$GT$$u20$as$u20$core..iter..traits..iterator..Iterator$GT$4next17h1023e5bd6414a678E (type 3) (param i32) (result i32)
    (local i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32 i32)
    global.get 0
    local.set 1
    i32.const 16
    local.set 2
    local.get 1
    local.get 2
    i32.sub
    local.set 3
    local.get 3
    global.set 0
    local.get 0
    i32.load
    local.set 4
    local.get 4
    call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$7is_null17hab6a48c9c6634120E
    drop
    i32.const 1
    local.set 5
    local.get 3
    local.get 5
    i32.store offset=4
    local.get 3
    i32.load offset=4
    local.set 6
    block  ;; label = @1
      local.get 6
      i32.eqz
      br_if 0 (;@1;)
      local.get 0
      i32.load offset=4
      local.set 7
      local.get 7
      call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$7is_null17hab6a48c9c6634120E
      drop
    end
    local.get 0
    i32.load
    local.set 8
    local.get 0
    i32.load offset=4
    local.set 9
    local.get 8
    local.set 10
    local.get 9
    local.set 11
    local.get 10
    local.get 11
    i32.eq
    local.set 12
    i32.const 1
    local.set 13
    local.get 12
    local.get 13
    i32.and
    local.set 14
    block  ;; label = @1
      block  ;; label = @2
        block  ;; label = @3
          local.get 14
          br_if 0 (;@3;)
          i32.const 1
          local.set 15
          local.get 3
          local.get 15
          i32.store offset=12
          local.get 3
          i32.load offset=12
          local.set 16
          block  ;; label = @4
            block  ;; label = @5
              local.get 16
              i32.eqz
              br_if 0 (;@5;)
              i32.const 1
              local.set 17
              local.get 0
              i32.load
              local.set 18
              local.get 0
              i32.load
              local.set 19
              local.get 19
              local.get 17
              call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$6offset17h9773484c37f3cdc3E
              local.set 20
              local.get 0
              local.get 20
              i32.store
              local.get 3
              local.get 18
              i32.store offset=8
              br 1 (;@4;)
            end
            i32.const -1
            local.set 21
            local.get 0
            i32.load offset=4
            local.set 22
            local.get 22
            local.get 21
            call $_ZN4core3ptr33_$LT$impl$u20$$BP$const$u20$T$GT$15wrapping_offset17h15f931fc13aca864E
            local.set 23
            local.get 0
            local.get 23
            i32.store offset=4
            local.get 0
            i32.load
            local.set 24
            local.get 3
            local.get 24
            i32.store offset=8
          end
          local.get 3
          i32.load offset=8
          local.set 25
          br 1 (;@2;)
        end
        i32.const 0
        local.set 26
        local.get 3
        local.get 26
        i32.store
        br 1 (;@1;)
      end
      local.get 3
      local.get 25
      i32.store
    end
    local.get 3
    i32.load
    local.set 27
    i32.const 16
    local.set 28
    local.get 3
    local.get 28
    i32.add
    local.set 29
    local.get 29
    global.set 0
    local.get 27
    return)
  (table (;0;) 1 1 funcref)
  (memory (;0;) 17)
  (global (;0;) (mut i32) (i32.const 1048576))
  (global (;1;) i32 (i32.const 1048590))
  (global (;2;) i32 (i32.const 1048590))
  (export "memory" (memory 0))
  (export "__data_end" (global 1))
  (export "__heap_base" (global 2))
  (export "main" (func $main))
  (data (;0;) (i32.const 1048576) "Hello, World!\0a"))
