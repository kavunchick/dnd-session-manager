--
-- PostgreSQL database dump
--

-- Dumped from database version 13.20 (Debian 13.20-1.pgdg120+1)
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: root
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: abilities; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.abilities (
    id bigint NOT NULL,
    description text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.abilities OWNER TO root;

--
-- Name: abilities_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.abilities_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.abilities_seq OWNER TO root;

--
-- Name: character_abilities; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.character_abilities (
    id bigint NOT NULL,
    session_character_id bigint NOT NULL,
    value smallint NOT NULL,
    ability_id bigint NOT NULL
);


ALTER TABLE public.character_abilities OWNER TO root;

--
-- Name: character_abilities_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.character_abilities_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.character_abilities_seq OWNER TO root;

--
-- Name: character_inventory; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.character_inventory (
    id bigint NOT NULL,
    amount integer NOT NULL,
    character_id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    inventory_id bigint NOT NULL
);


ALTER TABLE public.character_inventory OWNER TO root;

--
-- Name: character_inventory_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.character_inventory_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.character_inventory_seq OWNER TO root;

--
-- Name: characters; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.characters (
    id bigint NOT NULL,
    alignment smallint,
    background text,
    bonds text,
    flaws text,
    ideals text,
    imageuri text,
    name character varying(255) NOT NULL,
    personality_traits text,
    stats integer[],
    character_class bigint NOT NULL,
    author_id bigint NOT NULL,
    race_id bigint NOT NULL,
    CONSTRAINT characters_alignment_check CHECK (((alignment >= 0) AND (alignment <= 8)))
);


ALTER TABLE public.characters OWNER TO root;

--
-- Name: characters_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.characters_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.characters_seq OWNER TO root;

--
-- Name: classes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.classes (
    id bigint NOT NULL,
    description text,
    hit_point_die smallint NOT NULL,
    image text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.classes OWNER TO root;

--
-- Name: classes_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.classes_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.classes_seq OWNER TO root;

--
-- Name: equipment; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.equipment (
    id bigint NOT NULL,
    description text,
    name character varying(255) NOT NULL,
    suggested_price_gp smallint NOT NULL,
    weight double precision NOT NULL
);


ALTER TABLE public.equipment OWNER TO root;

--
-- Name: equipment_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.equipment_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.equipment_seq OWNER TO root;

--
-- Name: locations; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.locations (
    id bigint NOT NULL,
    description text,
    name character varying(255) NOT NULL,
    session_id bigint NOT NULL
);


ALTER TABLE public.locations OWNER TO root;

--
-- Name: locations_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.locations_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.locations_seq OWNER TO root;

--
-- Name: money; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.money (
    id bigint NOT NULL,
    currency character varying(255) NOT NULL,
    exchangeratecp double precision NOT NULL,
    exchangerateep double precision NOT NULL,
    exchangerategp double precision NOT NULL,
    exchangeratepp double precision NOT NULL,
    exchangeratesp double precision NOT NULL
);


ALTER TABLE public.money OWNER TO root;

--
-- Name: money_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.money_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.money_seq OWNER TO root;

--
-- Name: npc; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.npc (
    id bigint NOT NULL,
    alignment smallint,
    description text,
    health smallint NOT NULL,
    ishostile boolean NOT NULL,
    name character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    class_id bigint NOT NULL,
    location_id bigint,
    race_id bigint NOT NULL,
    CONSTRAINT npc_alignment_check CHECK (((alignment >= 0) AND (alignment <= 8)))
);


ALTER TABLE public.npc OWNER TO root;

--
-- Name: npc_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.npc_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.npc_seq OWNER TO root;

--
-- Name: race_ability_bonus; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.race_ability_bonus (
    id bigint NOT NULL,
    bonus smallint NOT NULL,
    ability_id bigint NOT NULL,
    race_id bigint NOT NULL
);


ALTER TABLE public.race_ability_bonus OWNER TO root;

--
-- Name: race_ability_bonus_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.race_ability_bonus_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.race_ability_bonus_seq OWNER TO root;

--
-- Name: race_traits; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.race_traits (
    id bigint NOT NULL,
    race_id bigint NOT NULL,
    trait_id bigint NOT NULL
);


ALTER TABLE public.race_traits OWNER TO root;

--
-- Name: race_traits_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.race_traits_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.race_traits_seq OWNER TO root;

--
-- Name: races; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.races (
    id bigint NOT NULL,
    description text,
    image text,
    name character varying(255) NOT NULL,
    size smallint NOT NULL,
    speed smallint NOT NULL,
    CONSTRAINT races_size_check CHECK (((size >= 0) AND (size <= 5)))
);


ALTER TABLE public.races OWNER TO root;

--
-- Name: races_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.races_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.races_seq OWNER TO root;

--
-- Name: session_character; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.session_character (
    id bigint NOT NULL,
    experience smallint NOT NULL,
    health smallint NOT NULL,
    level smallint NOT NULL,
    money integer[],
    stats integer[],
    character_id bigint,
    session_id bigint
);


ALTER TABLE public.session_character OWNER TO root;

--
-- Name: session_character_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.session_character_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.session_character_seq OWNER TO root;

--
-- Name: sessions; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.sessions (
    id bigint NOT NULL,
    last_updated timestamp(6) with time zone,
    name character varying(255) NOT NULL,
    start timestamp(6) with time zone NOT NULL,
    author_id bigint
);


ALTER TABLE public.sessions OWNER TO root;

--
-- Name: sessions_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.sessions_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sessions_seq OWNER TO root;

--
-- Name: traits; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.traits (
    id bigint NOT NULL,
    description text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.traits OWNER TO root;

--
-- Name: traits_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.traits_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.traits_seq OWNER TO root;

--
-- Name: users; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    sub character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO root;

--
-- Name: users_characters; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users_characters (
    user_id bigint NOT NULL,
    characters_id bigint NOT NULL
);


ALTER TABLE public.users_characters OWNER TO root;

--
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_seq OWNER TO root;

--
-- Name: users_sessions; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users_sessions (
    user_id bigint NOT NULL,
    sessions_id bigint NOT NULL
);


ALTER TABLE public.users_sessions OWNER TO root;

--
-- Name: abilities abilities_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.abilities
    ADD CONSTRAINT abilities_pkey PRIMARY KEY (id);


--
-- Name: character_abilities character_abilities_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.character_abilities
    ADD CONSTRAINT character_abilities_pkey PRIMARY KEY (id);


--
-- Name: character_inventory character_inventory_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.character_inventory
    ADD CONSTRAINT character_inventory_pkey PRIMARY KEY (id);


--
-- Name: characters characters_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.characters
    ADD CONSTRAINT characters_pkey PRIMARY KEY (id);


--
-- Name: classes classes_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_pkey PRIMARY KEY (id);


--
-- Name: equipment equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (id);


--
-- Name: users idx_sub; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT idx_sub UNIQUE (sub);


--
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: money money_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.money
    ADD CONSTRAINT money_pkey PRIMARY KEY (id);


--
-- Name: npc npc_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.npc
    ADD CONSTRAINT npc_pkey PRIMARY KEY (id);


--
-- Name: race_ability_bonus race_ability_bonus_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.race_ability_bonus
    ADD CONSTRAINT race_ability_bonus_pkey PRIMARY KEY (id);


--
-- Name: race_traits race_traits_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.race_traits
    ADD CONSTRAINT race_traits_pkey PRIMARY KEY (id);


--
-- Name: races races_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.races
    ADD CONSTRAINT races_pkey PRIMARY KEY (id);


--
-- Name: session_character session_character_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.session_character
    ADD CONSTRAINT session_character_pkey PRIMARY KEY (id);


--
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- Name: traits traits_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.traits
    ADD CONSTRAINT traits_pkey PRIMARY KEY (id);


--
-- Name: users_characters uk6jeengkpgflhel98rrfwrwy8e; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_characters
    ADD CONSTRAINT uk6jeengkpgflhel98rrfwrwy8e UNIQUE (characters_id);


--
-- Name: users_sessions ukgf9rd2660dwstodhx745l0k09; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_sessions
    ADD CONSTRAINT ukgf9rd2660dwstodhx745l0k09 UNIQUE (sessions_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: sessions fk17o34nhr5mna7s870subw0bvh; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT fk17o34nhr5mna7s870subw0bvh FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: users_sessions fk2wu231xghqf1hkk655po35qaq; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_sessions
    ADD CONSTRAINT fk2wu231xghqf1hkk655po35qaq FOREIGN KEY (sessions_id) REFERENCES public.sessions(id);


--
-- Name: character_inventory fk8eiqonhnyqu84oj0p1c14jp5o; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.character_inventory
    ADD CONSTRAINT fk8eiqonhnyqu84oj0p1c14jp5o FOREIGN KEY (inventory_id) REFERENCES public.session_character(id);


--
-- Name: session_character fk8g1gususx52gtwrt0tg4m216c; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.session_character
    ADD CONSTRAINT fk8g1gususx52gtwrt0tg4m216c FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- Name: users_characters fk9py8hxuv24mti9s3c6pjbok26; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_characters
    ADD CONSTRAINT fk9py8hxuv24mti9s3c6pjbok26 FOREIGN KEY (characters_id) REFERENCES public.characters(id);


--
-- Name: race_ability_bonus fk9qojk7smm5ie72eohjoal013s; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.race_ability_bonus
    ADD CONSTRAINT fk9qojk7smm5ie72eohjoal013s FOREIGN KEY (race_id) REFERENCES public.races(id);


--
-- Name: characters fk9y0iukg5f28663xfr8a0lrnlw; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.characters
    ADD CONSTRAINT fk9y0iukg5f28663xfr8a0lrnlw FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: npc fkbrutvksbfv5sbpq4tjuqu7ma2; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.npc
    ADD CONSTRAINT fkbrutvksbfv5sbpq4tjuqu7ma2 FOREIGN KEY (location_id) REFERENCES public.locations(id);


--
-- Name: npc fkeji2mnov04cv72iip8uxbav4i; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.npc
    ADD CONSTRAINT fkeji2mnov04cv72iip8uxbav4i FOREIGN KEY (class_id) REFERENCES public.classes(id);


--
-- Name: race_traits fkf7i1p5agqh74s32hp9pi3pc05; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.race_traits
    ADD CONSTRAINT fkf7i1p5agqh74s32hp9pi3pc05 FOREIGN KEY (trait_id) REFERENCES public.traits(id);


--
-- Name: character_inventory fkff5evlm9tl94upk6aw2e1cdhh; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.character_inventory
    ADD CONSTRAINT fkff5evlm9tl94upk6aw2e1cdhh FOREIGN KEY (equipment_id) REFERENCES public.equipment(id);


--
-- Name: users_characters fkgrcv637viq4qvknnhybntgu8b; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_characters
    ADD CONSTRAINT fkgrcv637viq4qvknnhybntgu8b FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: users_sessions fkhujx8d7x0w6s3qkkii51qfyml; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users_sessions
    ADD CONSTRAINT fkhujx8d7x0w6s3qkkii51qfyml FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: npc fki72q3t03d32owvk9upi1x0c6d; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.npc
    ADD CONSTRAINT fki72q3t03d32owvk9upi1x0c6d FOREIGN KEY (race_id) REFERENCES public.races(id);


--
-- Name: character_abilities fkj0w4p4gvyjj4kc7ddc1ycpfgd; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.character_abilities
    ADD CONSTRAINT fkj0w4p4gvyjj4kc7ddc1ycpfgd FOREIGN KEY (ability_id) REFERENCES public.abilities(id);


--
-- Name: race_ability_bonus fkk6j5wqi9tqnfyohwqn1vl0fsw; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.race_ability_bonus
    ADD CONSTRAINT fkk6j5wqi9tqnfyohwqn1vl0fsw FOREIGN KEY (ability_id) REFERENCES public.abilities(id);


--
-- Name: characters fkll6wc8r1jc3kxldc22jevaq47; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.characters
    ADD CONSTRAINT fkll6wc8r1jc3kxldc22jevaq47 FOREIGN KEY (character_class) REFERENCES public.classes(id);


--
-- Name: characters fko6gm7oj0xscb6p54jaswr8sb6; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.characters
    ADD CONSTRAINT fko6gm7oj0xscb6p54jaswr8sb6 FOREIGN KEY (race_id) REFERENCES public.races(id);


--
-- Name: session_character fkqw4cpoc82h9humxinlm0kho4v; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.session_character
    ADD CONSTRAINT fkqw4cpoc82h9humxinlm0kho4v FOREIGN KEY (character_id) REFERENCES public.characters(id);


--
-- Name: locations fkrc26fc71lx6jnar97n361a7vs; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT fkrc26fc71lx6jnar97n361a7vs FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- Name: race_traits fkritgnsw7hilyn9krp64nx6g5m; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.race_traits
    ADD CONSTRAINT fkritgnsw7hilyn9krp64nx6g5m FOREIGN KEY (race_id) REFERENCES public.races(id);


--
-- Name: character_inventory fkrsp29quovbcatl4ra3oe25l4k; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.character_inventory
    ADD CONSTRAINT fkrsp29quovbcatl4ra3oe25l4k FOREIGN KEY (character_id) REFERENCES public.session_character(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: root
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

