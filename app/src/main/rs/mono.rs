#pragma version(1)
#pragma rs java_package_name(com.just.sun.rs)


const static float3 gMonoMult1 = {0.299f, 0.587f, 0.114f};

void root(const uchar4 *v_in, uchar4 *v_out) {
    float4 f4 = rsUnpackColor8888(*v_in);//将输入的颜色值转化为r g b a
    float3 mono = dot(f4.rgb, gMonoMult1);//计算向量的点积
    *v_out = rsPackColorTo8888(mono);
}