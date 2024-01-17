PGDMP     '    2                 |            staging_commerce    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    24737    staging_commerce    DATABASE     �   CREATE DATABASE staging_commerce WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
     DROP DATABASE staging_commerce;
                postgres    false            �            1255    24804    updated_at_stock()    FUNCTION     �   CREATE FUNCTION public.updated_at_stock() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    RAISE NOTICE 'updated_at_stock()';
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$;
 )   DROP FUNCTION public.updated_at_stock();
       public          postgres    false            �            1259    24738    m_inventory    TABLE     +  CREATE TABLE public.m_inventory (
    id bigint NOT NULL,
    name character varying(128),
    price bigint,
    stock bigint,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    deleted_at timestamp without time zone,
    description text,
    image text
);
    DROP TABLE public.m_inventory;
       public         heap    postgres    false            �            1259    24799    m_inventory_seq    SEQUENCE     y   CREATE SEQUENCE public.m_inventory_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.m_inventory_seq;
       public          postgres    false            �            1259    24761    t_order    TABLE     �   CREATE TABLE public.t_order (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    deleted_at timestamp without time zone,
    invoice_no character varying,
    total_bill bigint
);
    DROP TABLE public.t_order;
       public         heap    postgres    false            �            1259    24768    t_order_detail    TABLE     Z  CREATE TABLE public.t_order_detail (
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    product_id bigint,
    quantity bigint,
    amount bigint,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    deleted_at timestamp without time zone,
    total_amount bigint,
    name character varying(128)
);
 "   DROP TABLE public.t_order_detail;
       public         heap    postgres    false            �            1259    24800    t_order_detail_seq    SEQUENCE     |   CREATE SEQUENCE public.t_order_detail_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.t_order_detail_seq;
       public          postgres    false            �            1259    24801    t_order_seq    SEQUENCE     u   CREATE SEQUENCE public.t_order_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.t_order_seq;
       public          postgres    false                      0    24738    m_inventory 
   TABLE DATA           u   COPY public.m_inventory (id, name, price, stock, created_at, updated_at, deleted_at, description, image) FROM stdin;
    public          postgres    false    214   J                 0    24761    t_order 
   TABLE DATA           a   COPY public.t_order (id, created_at, updated_at, deleted_at, invoice_no, total_bill) FROM stdin;
    public          postgres    false    215   �                 0    24768    t_order_detail 
   TABLE DATA           �   COPY public.t_order_detail (id, order_id, product_id, quantity, amount, created_at, updated_at, deleted_at, total_amount, name) FROM stdin;
    public          postgres    false    216   �                  0    0    m_inventory_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.m_inventory_seq', 251, true);
          public          postgres    false    217                       0    0    t_order_detail_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.t_order_detail_seq', 1051, true);
          public          postgres    false    218                       0    0    t_order_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.t_order_seq', 1251, true);
          public          postgres    false    219            q           2606    24742    m_inventory m_inventory_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.m_inventory
    ADD CONSTRAINT m_inventory_pk PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.m_inventory DROP CONSTRAINT m_inventory_pk;
       public            postgres    false    214            u           2606    24772     t_order_detail t_order_detail_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.t_order_detail
    ADD CONSTRAINT t_order_detail_pk PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.t_order_detail DROP CONSTRAINT t_order_detail_pk;
       public            postgres    false    216            s           2606    24767    t_order t_order_pk 
   CONSTRAINT     P   ALTER TABLE ONLY public.t_order
    ADD CONSTRAINT t_order_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.t_order DROP CONSTRAINT t_order_pk;
       public            postgres    false    215            v           2620    24805    m_inventory update_stock    TRIGGER     x   CREATE TRIGGER update_stock AFTER UPDATE ON public.m_inventory FOR EACH ROW EXECUTE FUNCTION public.updated_at_stock();
 1   DROP TRIGGER update_stock ON public.m_inventory;
       public          postgres    false    220    214               @  x���]o�0��ï�.����G�H�TVU�(��IH萸��8.6������L��Y���s���Oa��l�и]���� �	�$`��!�!�#��\��LP���P���8��ѿEQ)u�A?��R�_na�!��36�"����q��\\YN�h�H5PI����G��Q����k����_���4fp��	"��]���Y�(�6�ޜ}>��Q���(D�Z�@��L��J��(�K�$=��8�3��I(�9�'�&��<���If��h�k�O3�%��~�ǃ�*;#@��h�VP/�n����O�\A�PV�Jx���b#<P��Q�Zo��џ'RD�~���Y�Y�Y��1�`��]�<�o�[��e[����U�u[n
g#e�����o��B��*~5�]!5ow���,g������A�C����_���X��(��Ǧ��
p�j�G�HE�H8�|f�^��L���<�8=b"�V�{܀�WH�m��$y	jه��ޔ69�e"�Hn�r�1���)}v�}v��B-�Q�7`�z�E�����?�
A�{�[���Lq���dKq�         K   x�3420�b]C]CsC+cK+#=KK#C,R�z���1~��~a� ��溆 �,@�+F��� 3�V         |   x�3400�44�F�@� 8�&�����
�VƖV&Fz�f�f�f�1~ dQ�����YT����^Z�������Z���4�b0� ��m����%�\3��ީ��Y�
�9�`��+F��� 8_%�     